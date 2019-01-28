package cn.luckydeer.spider.common.memcached.impl;

import net.spy.memcached.KeyUtil;
import net.spy.memcached.MemcachedClientIF;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.luckydeer.spider.common.memcached.DistributedCached;
import cn.luckydeer.spider.common.memcached.constant.CachedConstants;

public abstract class AbstractTairCached implements DistributedCached {

    /** 系统日志*/
    private final static Logger systemLogger = LoggerFactory.getLogger("MEMCACHED-LOG");

    protected boolean validateValue(Object value) {
        if (null == value) {
            systemLogger.error("value 不能为空");
            return false;
        }
        return true;
    }

    protected boolean validateExp(int exp) {
        if (exp <= 0) {
            systemLogger.error("超时时间不合法,exp= " + exp);
            return false;
        }
        // 不得超过30天缓存
        if (exp > CachedConstants.MAX_CACHE_TIME) {
            systemLogger.error("超时时间不得超过30天,exp= " + exp);
            return false;
        }

        return true;
    }

    protected boolean validateKey(String key) {

        if (StringUtils.isBlank(key)) {
            systemLogger.error("Key can not blank");
            return false;
        }

        byte[] keyBytes = KeyUtil.getKeyBytes(key);
        if (keyBytes.length >= MemcachedClientIF.MAX_KEY_LENGTH) {
            systemLogger.error("Key is too long (maxlen = " + MemcachedClientIF.MAX_KEY_LENGTH
                               + "key= " + key);
            return false;
        }
        if (keyBytes.length == 0) {
            systemLogger.error("Key must contain at least one character.");
            return false;
        }
        // Validate the key
        for (byte b : keyBytes) {
            if (b == ' ' || b == '\n' || b == '\r') {
                systemLogger.error("key不能包含空格、换行符:  ``" + key);
                return false;
            }
        }
        return true;
    }

}
