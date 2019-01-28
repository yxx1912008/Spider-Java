package cn.luckydeer.spider.common.memcached.config;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Memcached 缓存配置
 * 
 * @author yuanxx
 * @version $Id: MemcacheSourceConfig.java, v 0.1 2019年1月28日 下午2:28:36 yuanxx Exp $
 */
@Configuration
public class MemcacheSourceConfig {

    private final static Logger logger = LoggerFactory.getLogger("MEMCACHED-LOG");

    @Value(value = "${memcache.ip}")
    private String              ip;
    @Value(value = "${memcache.port}")
    private int                 port;

    @Bean(name = "memCachedClient")
    public MemcachedClient memCachedClient() {
        try {
            return new MemcachedClient(new InetSocketAddress(ip, port));
        } catch (IOException e) {
            logger.error("Memcached缓存连接出错", e);
        }
        return null;
    }

}
