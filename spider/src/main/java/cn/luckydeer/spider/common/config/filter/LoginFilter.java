package cn.luckydeer.spider.common.config.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import cn.luckydeer.spider.common.view.response.ResponseObj;
import cn.luckydeer.spider.manager.user.UserManager;

import com.alibaba.fastjson.JSON;

public class LoginFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger("FILTER");

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // logger.info("拦截器生效,url :" + request.getRequestURI());

        /** 判断是否是optinons请求  */
        if (StringUtils.equalsIgnoreCase(request.getMethod(), HttpMethod.OPTIONS.name())) {
            doOptions(request, response);
            return;
        }

        boolean flag = false;

        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            logger.info("当前用户未登录:" + request.getContextPath());
            request.getRequestDispatcher("/user/loginError").forward(request, response);
            return;
        } else {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals("userInfo", cookie.getName())) {
                    boolean result = UserManager.isLoginUser(cookie.getValue(), request, response);
                    if (result) {
                        flag = true;
                    }
                }
            }
        }

        if (!flag) {
            logger.info("当前用户未登录:" + request.getContextPath());
            request.getRequestDispatcher("/user/loginError").forward(request, response);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 
     * 注解：
     * @param request
     * @param response
     * @author yuanxx @date 2018年12月11日
     */
    public static void doOptions(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println(JSON.toJSON(new ResponseObj("OPTIONS")));
        } catch (Exception e) {
            logger.error("json输出失败", e);
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (Exception e) {
                logger.error("json关闭异常", e);
            }
            writer = null;
        }
        return;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
