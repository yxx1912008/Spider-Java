package cn.luckydeer.spider.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.luckydeer.spider.common.config.filter.LoginFilter;

/**
 * 
 * 拦截器配置
 * 
 * @author yuanxx
 * @version $Id: FilterConfig.java, v 0.1 2018年11月15日 上午9:33:00 yuanxx Exp $
 */
@Configuration
public class FilterConfig {

    /**
     * 
     * 注解：通用拦截器
     * @return
     * @author yuanxx @date 2018年10月30日
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean myFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());
        registration.addUrlPatterns("*.web");
        registration.addInitParameter("FilterName", "common");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }

}
