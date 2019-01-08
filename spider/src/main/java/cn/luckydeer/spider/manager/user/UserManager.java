package cn.luckydeer.spider.manager.user;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.luckydeer.spider.common.model.user.UserInfo;
import cn.luckydeer.spider.common.utils.date.DateUtilSelf;
import cn.luckydeer.spider.common.utils.valid.DesUtil;

import com.alibaba.fastjson.JSON;

/**
 * 用户管理类
 * 
 * @author yuanxx
 * @version $Id: UserManager.java, v 0.1 2018年12月5日 下午3:27:04 yuanxx Exp $
 */
@Service(value = "userManager")
public class UserManager {

    /** 用户名  */
    private final static String USER_NAME = "qufu666";

    /** 用户密码  */
    private final static String USER_PWD  = "qufu666";

    private final static Logger logger    = LoggerFactory.getLogger("SYS-LOG");

    /**
     * 
     * 注解：登录
     * @return
     * @author yuanxx @date 2018年12月5日
     */
    public String login(String uName, String pwd) {
        if (StringUtils.equals(pwd, USER_PWD) && StringUtils.equals(uName, USER_NAME)) {
            return getTicket();
        }
        return null;
    }

    /**
     * 
     * 注解：获取ticket
     * @return
     * @author yuanxx @date 2018年12月5日
     */
    public String getTicket() {
        UserInfo info = new UserInfo();
        info.setPassword(USER_PWD);
        info.setuName(USER_NAME);
        /**  设置超时时间为五分钟以后 */
        Date failTime = DateUtilSelf.increaseMinute(60 * 24 * 20);
        info.setFailTime(failTime);
        String sign = JSON.toJSONString(info);
        return DesUtil.encrypt(sign);
    }

    /**
     * 
     * 注解：判断是否是登录用户
     * @param sign
     * @return
     * @author yuanxx @date 2018年12月5日
     */
    public static boolean isLoginUser(String sign, HttpServletRequest request,
                                      HttpServletResponse response) {

        if (StringUtils.isNotBlank(sign)) {
            String result = DesUtil.decrypt(sign);
            if (StringUtils.isNotBlank(result)) {
                try {
                    UserInfo info = JSON.parseObject(result, UserInfo.class);
                    if (null == info) {
                        return false;
                    }
                    if (StringUtils.equals(info.getPassword(), USER_PWD)
                        && StringUtils.equals(info.getuName(), USER_NAME)) {
                        int calcTime = DateUtilSelf.calculateDecreaseSecond(info.getFailTime(),
                            new Date());
                        if (calcTime >= 0) {
                            return false;
                        }
                        return true;
                    }
                    return false;
                } catch (Exception e) {
                    logger.error("验签失败：" + sign, e);
                    return false;
                }
            }
        }
        return false;
    }

}
