package cn.luckydeer.spider.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * 系统配置
 * 
 * @author yuanxx
 * @version $Id: SysConfig.java, v 0.1 2018年11月15日 上午9:19:33 yuanxx Exp $
 */
@Configuration(value = "sysConfig")
public class SysConfig {

    /**  读取 当前是否是测试环境  */
    @Value(value = "${current.environment}")
    private String        vmConfigValue;

    private static String domain;

    /**
     * 
     * 注解：是否是测试环境
     * @return
     * @author yuanxx @date 2018年11月15日
     */
    @Bean
    public String isDebug() {
        return this.vmConfigValue;
    }

    public void getDomain(String domain) {
        SysConfig.domain = domain;
    }

    public static String getDomain() {
        return domain;
    }

    @Value(value = "${server.domain}")
    public void setDomain(String domain) {
        SysConfig.domain = domain;
    }

}
