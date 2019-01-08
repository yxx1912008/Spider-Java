/**
 * icaomei Inc.
 * Copyright (c) 2016-2016 All Rights Reserved.
 */
package cn.luckydeer.spider.common.view.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * 域设置
 * @author panwuhai
 * @version $Id: DomainUtils.java, v 0.1 2016年6月28日 上午10:48:21 panwuhai Exp $
 */
public class DomainUtils {

    /**
     * 注解：
     * 参考：http://blog.csdn.net/mygrilzhuyulin/article/details/52690129
     * 前端关键加入
     * angular.module("app").config(function ($httpProvider) {
     * $httpProvider.defaults.withCredentials = true;
     * $httpProvider.defaults.headers.common['Authorization'] = "89757";
     * })
     * 
     * ajax:
     * jquery的post方法请求：
     *
     * $.ajax({
     *   type: "POST",
     *   url: "http://xxx.com/api/test",
     *  dataType: 'jsonp',
     *  xhrFields: {withCredentials: true},
     *  crossDomain: true,
     * })
     * 
     * 后台关键
     * response.setHeader("Access-Control-Allow-Credentials", "true");
     * @param request
     * @param response
     * @author panwuhai @date 2017年5月24日
     */
    public static void setAccessContrlAllowOrigin(HttpServletRequest request,
                                                  HttpServletResponse response) {
        /*
         * origin参数指定一个允许向该服务器提交请求的域名(eg:http://www.***.com;多个域名使用逗号分隔)，*：允许任意域名跨域访问资源，生产库域名申请完成后需配置服务器明确的域名
         */
        // 带cookie不能使用 * 号
        /*        response
                    .setHeader(
                        "Access-Control-Allow-Origin",
                        "member.prod.icaomei.com,trade.prod.icaomei.com,member.icaomei.com,trade.icaomei.com,192.168.18.226,192.168.18.108");*/

        // 动态获取异步前端 域名 http://view.icaomei.com
        String origin = request.getHeader("Origin");
        if (StringUtils.isNotBlank(origin)) {
            if (origin.indexOf(".qufx.mobi") >= 0) {
                response.setHeader("Access-Control-Allow-Origin", origin);
                /*
                 * 默认情况下，跨源请求不提供凭据(cookie、HTTP认证及客户端SSL证明等)。
                 * 通过将withCredentials属性设置为true，可以指定某个请求应该发送凭据。如果服务器接收带凭据的请求，会用下面的HTTP头部来响应
                 * 如果发送的是带凭据的请求，但服务器的相应中没有包含这个头部，那么浏览器就不会把相应交给JavaScript(于是，responseText中将是空字符串，status的值为0，而且会调用onerror()事件处理程序)
                 */
                response.setHeader("Access-Control-Allow-Credentials", "true");
            } else {
                response.setHeader("Access-Control-Allow-Origin", origin);
                response.setHeader("Access-Control-Allow-Credentials", "true");
            }
        } else {
            // app 请求无该值，也不受跨域影响
            response.setHeader("Access-Control-Allow-Origin", "*");
        }

        // response.setHeader("Content-Type", "application/json;charset=utf-8");
        /*
         * 
         * 指明资源可以被请求的方式有哪些(一个或者多个). 这个响应头信息在客户端发出预检请求的时候会被返回
         */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,HEAD,OPTIONS,PUT,DELETE");
        /*
         * 设置浏览器允许访问的服务器的头信息的白名单
         * 将 crossdomain.xml 放置tomcat/webapp下的ROOT里
         * deviceNo, loginChannel, sign, sysVersion, terminalDevice, timeStamp, ticket, Set-Cookie
         * 
         * Accept, Authorization, If-Modified-Since, Last-Modified, Cache-Control, Expires, X-E4M-With, Server
         */
        response.setHeader("Access-Control-Allow-Headers",
            "Origin,X-Requested-With,Content-Type,Set-Cookie, userInfo");
        /*
         * 一次预请求的结果的有效时间(秒)，在该时间内对同一个请求不再发送预请求
         * 3600*24*30 = 2592000 一个月
         */
        response.setHeader("Access-Control-Max-Age", "2592000");
    }

}
