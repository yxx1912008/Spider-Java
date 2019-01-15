package cn.luckydeer.spider.manager.wechat;

import io.github.biezhi.wechat.api.constant.Config;

import org.springframework.stereotype.Service;

/**
 * 
 * 
 * @author yuanxx
 * @version $Id: WechatManager.java, v 0.1 2019年1月14日 下午1:51:32 yuanxx Exp $
 */
@Service(value = "wechatManager")
public class WechatManager {

    private Robot robot;

    /**
     * 
     * 注解：初始化机器人 
     * @return
     * @author yuanxx @date 2019年1月14日
     */
    public boolean initRobot() {
        robot = new Robot(Config.me().autoLogin(false).showTerminal(true));
        return true;
    }

    /**
     * 
     * 注解：启动机器人
     * @return
     * @author yuanxx @date 2019年1月14日
     */
    public boolean startRob() {
        robot.start();
        return true;
    }

    /**
     * 
     * 注解：获取二维码
     * @return
     * @author yuanxx @date 2019年1月14日
     */
    public String codeUrl() {
        return robot.getCodeUrl();
    }

    /**
     * 
     * 注解：退出登录
     * @return
     * @author yuanxx @date 2019年1月14日
     */
    public boolean loginOut() {
        robot.loginOut();
        robot.setCanLogOut(true);
        return true;
    }

    public boolean isRunning() {
        return robot.isRunning();
    }

}
