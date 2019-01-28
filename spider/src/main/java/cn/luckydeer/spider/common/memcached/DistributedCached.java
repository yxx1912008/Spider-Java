package cn.luckydeer.spider.common.memcached;

import cn.luckydeer.spider.common.memcached.enums.CachedType;

/**
 * 分布式缓存接口
 * 
 * @author yuanxx
 * @version $Id: DistributedCached.java, v 0.1 2019年1月28日 下午2:34:12 yuanxx Exp $
 */
public interface DistributedCached {

    /**
     * 获取缓存 
     * @param cachedType  缓存类型
     * @param key  【长度<250; 不能包含空格、换行符;】
     * @return
     */
    Object get(CachedType cachedType, String key);

    /**
     * 删除缓存
     * @param cachedType  缓存类型
     * @param key
     * @return
     */
    boolean remove(CachedType cachedType, String key);

    /**
     * 默认保存24小时
     * @param cachedType  缓存类型
     * @param key
     * @param value
     * @return
     */
    boolean put(CachedType cachedType, String key, Object value);

    /**
     * @param cachedType  缓存类型
     * @param key
     * @param exp  单位秒 【不得超过30天   30 * 24 * 60 * 60】
     * @param value
     * @return
     */
    boolean put(CachedType cachedType, String key, int exp, Object value);

    /**
     * 注解：非阻塞式分布式锁
     * @param cachedType
     * @param keyMutex
     * @param value
     * @return
     * @author panwuhai @date 2017年6月27日
     */
    boolean nonblock(CachedType cachedType, String keyMutex);

    /**
     * 注解：阻塞式分布式同步锁
     * @param cachedType
     * @param keyMutex
     * @return
     * @author panwuhai @date 2017年8月21日
     */
    boolean block(CachedType cachedType, String keyMutex);

    /**
     * 注解：解锁
     * @param cachedType
     * @param keyMutex
     * @return
     * @author panwuhai @date 2017年6月27日
     */
    boolean unlock(CachedType cachedType, String keyMutex);

    /**
     * 数据切换清理所有缓存
     * @return
     */
    boolean cleanAll();

}
