package cn.luckydeer.spider.controller.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.luckydeer.spider.common.view.response.ResponseObj;
import cn.luckydeer.spider.common.view.show.ViewShowEnums;
import cn.luckydeer.spider.manager.user.UserManager;

/**
 * 
 * 登录
 * 
 * @author yuanxx
 * @version $Id: LoginController.java, v 0.1 2018年12月5日 下午3:34:19 yuanxx Exp $
 */
@RequestMapping(value = "/user")
@RestController
public class LoginController {

    @Autowired
    private UserManager userManager;

    /**
     * 
     * 注解：登录接口
     * @param uName
     * @param password
     * @param request
     * @param response
     * @return
     * @author yuanxx @date 2018年12月5日
     */
    @RequestMapping(value = "/login")
    public ResponseObj login(String uName, String password, HttpServletRequest request,
                             HttpServletResponse response) {

        if (StringUtils.isBlank(uName) || StringUtils.isBlank(password)) {
            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "用户名密码不能为空");
        }
        String result = userManager.login(uName, password);
        if (StringUtils.isNotBlank(result)) {
            Cookie cookie = new Cookie("userInfo", result);
            // 要使此处有效 则访问地址必须与此处的域名一致
            // 设置父path
            //cookie.setDomain(SysConfig.getDomain());//配置cookie的domain
            cookie.setPath("/");
            // 防xss漏洞
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 24 * 20);//周期为一天 60 * 60 * 24
            response.addCookie(cookie);
            return new ResponseObj();
        }
        return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "登录失败");
    }

    /**
     * 
     * 注解：退出登录
     * @param uName
     * @param request
     * @param response
     * @return
     * @author yuanxx @date 2018年12月6日
     */
    @RequestMapping(value = "/loginOut")
    @ResponseBody
    public ResponseObj loginOut(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("userInfo", "");
        //cookie.setDomain(SysConfig.getDomain());
        cookie.setMaxAge(5);
        cookie.setPath("/");
        response.addCookie(cookie);
        ResponseObj result = new ResponseObj();
        return result;
    }

    @RequestMapping(value = "/loginError")
    @ResponseBody
    public ResponseObj loginError(HttpServletRequest request, HttpServletResponse response) {
        ResponseObj result = new ResponseObj(ViewShowEnums.NOT_LOGIN.getStatus(), "用户未登录");
        return result;
    }

}
