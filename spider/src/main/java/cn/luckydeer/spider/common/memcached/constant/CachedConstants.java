package cn.luckydeer.spider.common.memcached.constant;

/**
 * 缓存常量
 * 
 * @author yuanxx
 * @version $Id: CachedConstants.java, v 0.1 2019年1月28日 下午2:31:49 yuanxx Exp $
 */
public interface CachedConstants {

    /** 最大缓存时间 */
    int    MAX_CACHE_TIME = 30 * 24 * 60 * 60;

    /**
     *树莓派缓存全局锁前缀
     *根据业务制定的缓存业务前缀|全局锁前缀
     *分布式缓存限制 key长度 < 250 value < 1m
     */
    String PREFIX         = "RASP_";

}
