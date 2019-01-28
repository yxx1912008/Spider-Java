package cn.luckydeer.spider.common.memcached.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.luckydeer.spider.common.memcached.DistributedCached;
import cn.luckydeer.spider.common.memcached.enums.CachedType;

import com.alibaba.fastjson.JSON;

/**
 * 分布式缓存
 * 32位操作系统一个端口支持最大内存2G
 * @author yuanxx
 * @version $Id: DistributedCachedImpl.java, v 0.1 2019年1月28日 下午3:01:49 yuanxx Exp $
 */
@Service(value = "distributedCached")
public class DistributedCachedImpl extends AbstractTairCached implements DistributedCached {

    /** 系统日志*/
    private final static Logger          systemLogger = LoggerFactory.getLogger("MEMCACHED-LOG");

    /** 互斥常量 （业务数据与锁数据分离，不覆盖）*/
    private static final String          mutex        = "_MUTEX";

    /** 缓存器链 */
    private Map<String, MemcachedClient> cachedMap    = new ConcurrentHashMap<String, MemcachedClient>();

    @Autowired
    private MemcachedClient              memCachedClient;

    /**
     * 
     * 注解：初始化
     * 该类在实例化以后执行此初始化方法
     * @author yuanxx @date 2018年10月30日
     */
    @PostConstruct
    public void init() {
        systemLogger.info("Memcached开始初始化");
        cachedMap.put(CachedType.BUSINESS_CACHE.getCode(), memCachedClient);
    }

    public Object get(CachedType cachedType, String key) {
        if (validateKey(key)) {
            try {
                return cachedMap.get(cachedType.getCode()).get(key);
            } catch (Exception e) {
                systemLogger.error("读取分布式缓存异常,key=" + key, e);
                return null;
            }
        }
        return null;
    }

    public boolean remove(CachedType cachedType, String key) {
        if (validateKey(key)) {
            try {
                cachedMap.get(cachedType.getCode()).delete(key);
                return true;
            } catch (Exception e) {
                systemLogger.error("删除分布式缓存异常,key=" + key, e);
                return false;
            }
        }
        return false;
    }

    /**
     * 默认缓存一天时间的数据
     * 
     * @param key
     * @param value
     * @return
     */
    public boolean put(CachedType cachedType, String key, Object value) {
        return put(cachedType, key, 24 * 60 * 60, value);
    }

    /**
     * @param key
     * @param exp
     *            超时时间:单位秒
     * @param value
     * @return
     */
    public boolean put(CachedType cachedType, String key, int exp, Object value) {

        if (validateKey(key) && validateExp(exp) && validateValue(value)) {
            try {
                cachedMap.get(cachedType.getCode()).set(key, exp, value);
                return true;
            } catch (Exception e) {
                systemLogger.error("缓存内部系统异常,保存失败key=" + key, e);
                return false;
            }
        }
        return false;
    }

    /**
     * 注解：非阻塞式分布式锁
     * @param cachedType
     * @param key
     * @param value
     * @return
     * @author panwuhai @date 2017年6月27日
     */
    public boolean nonblock(CachedType cachedType, String key) {
        String threadName = Thread.currentThread().getName();
        final String lockKey = key + mutex;
        if (validateKey(lockKey)) {
            try {
                // 避免宕机死锁  30秒锁失效时间
                OperationFuture<Boolean> future = cachedMap.get(cachedType.getCode()).add(lockKey,
                    30, Boolean.TRUE);
                boolean flag = future.get().booleanValue();
                if (flag) {
                    systemLogger.info(threadName + ": " + key + ": 上锁成功");
                }
                return flag;
            } catch (InterruptedException e) {
                systemLogger.error("缓存内部系统异常,锁失败key=" + key, e);
            } catch (ExecutionException e) {
                systemLogger.error("缓存内部系统异常,锁失败key=" + key, e);
            }
        }
        return false;
    }

    /**
     * 注解：阻塞式分布式锁
     * @param cachedType
     * @param keyMutex
     * @return
     * @author panwuhai @date 2017年6月27日
     */
    public boolean block(CachedType cachedType, String key) {

        String threadName = Thread.currentThread().getName();
        long startTime = System.currentTimeMillis();
        long excuteTime = 50;
        int synchronizedNum = 0;
        final String lockKey = key + mutex;
        if (validateKey(lockKey)) {
            try {
                // 避免宕机死锁  300秒锁失效时间
                while (true) {
                    OperationFuture<Boolean> future = cachedMap.get(cachedType.getCode()).add(
                        lockKey, 30, Boolean.TRUE);
                    if (future.get().booleanValue()) {
                        long diffTime = System.currentTimeMillis() - startTime;
                        systemLogger.info(threadName + ": " + key + ": 上锁成功,方法同步次数synchronizedNum="
                                          + synchronizedNum + ";时间=" + diffTime + "ms");

                        return true;
                    }
                    ++synchronizedNum;
                    // 测试用100个并发线程，计算出来的最优时差为睡眠时间==具体方法的执行时间
                    // 方法执行时间50ms,阻塞sleep(60) 结果 6127ms处理100个并发线程 17:09:32,341 - 17:09:26,214
                    // 方法执行时间50ms,无睡眠阻塞 结果 5197ms处理100个并发线程 10:25:13,809 - 10:25:08,612(虽然快，对缓存网络频繁调用的压力过大不建议使用)
                    // 方法执行时间50ms,阻塞sleep(50) 结果 5310ms处理100个并发线程 16:59:07,801 - 16:59:02,491
                    // 方法执行时间50ms,阻塞sleep(50) 结果 5378ms处理100个并发线程 17:11:58,943 - 17:11:53,565
                    // 方法执行时间50ms,阻塞sleep(30) 结果 6230ms处理100个并发线程 17:04:11,607 - 17:04:05,377
                    // 方法执行时间50ms,阻塞sleep(35) 结果 6261ms处理100个并发线程 17:06:05,939 - 17:05:59,678   
                    if (synchronizedNum > 100) {
                        long diffTime = System.currentTimeMillis() - startTime;
                        systemLogger.error(threadName + ": " + key
                                           + ": 上锁失败;方法同步次数synchronizedNum=" + synchronizedNum
                                           + ";时间=" + diffTime + "ms");
                        return false;
                    }

                    Thread.sleep(excuteTime);
                }
            } catch (Exception e) {
                systemLogger.error("缓存内部系统异常,锁失败key=" + key, e);
            }
        }
        throw new RuntimeException("调用缓存错误key=" + key);
    }

    /**
     * 注解：解锁
     * @param cachedType
     * @param key
     * @param exp  秒为单位 
     * @param value
     * @return
     * @author panwuhai @date 2017年6月27日
     */
    public boolean unlock(CachedType cachedType, String key) {
        String threadName = Thread.currentThread().getName();
        final String lockKey = key + mutex;
        if (validateKey(lockKey)) {
            try {
                OperationFuture<Boolean> future = cachedMap.get(cachedType.getCode()).delete(
                    lockKey);
                boolean flag = future.get().booleanValue();
                if (flag) {
                    systemLogger.info(threadName + ": " + key + ": 解锁成功");
                } else {
                    systemLogger.error(threadName + ": " + key + ": 解锁失败");
                }
                return flag;
            } catch (InterruptedException e) {
                systemLogger.error("缓存内部系统异常,解锁失败key=" + key, e);
            } catch (ExecutionException e) {
                systemLogger.error("缓存内部系统异常,解锁失败key=" + key, e);
            }
        }
        return false;
    }

    /**
     * 清理缓存
     * @return
     */
    public boolean cleanAll() {
        if (!CollectionUtils.isEmpty(cachedMap)) {
            for (Map.Entry<String, MemcachedClient> entry : cachedMap.entrySet()) {
                OperationFuture<Boolean> future = entry.getValue().flush();
                systemLogger.info(future.getStatus() + ": " + JSON.toJSONString(future));
            }
        }
        return true;
    }

}
