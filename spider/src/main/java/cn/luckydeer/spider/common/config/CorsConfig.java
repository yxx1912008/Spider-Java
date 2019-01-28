package cn.luckydeer.spider.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * cros配置
 * 避免出现跨域的情况
 * 
 * @author yuanxx
 * @version $Id: CorsConfig.java, v 0.1 2018年12月21日 上午9:31:40 yuanxx Exp $
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value(value = "${server.domain}")
    private String domain;

    /**
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry)
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry
            .addMapping("/**")
            .allowedOrigins("http://qufubackstage.qufx.mobi", "http://pc.qufx.mobi", "127.0.0.1",
                domain)
            .maxAge(259200)
            .allowCredentials(true)
            .allowedHeaders("Origin", "accept", "X-Requested-With", "Content-Type", "Set-Cookie",
                "X-Custom-Header", "Access-Control-Request-Method", "userInfo")
            .allowedMethods("POST", "GET", "OPTIONS", "HEAD", "PUT", "DELETE");

    }

}
