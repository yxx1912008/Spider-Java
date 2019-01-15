package cn.luckydeer.spider.manager.wechat;

import io.github.biezhi.wechat.api.constant.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.luckydeer.spider.common.utils.thread.ExecutorServiceUtils;

/**
 * 
 * 
 * @author yuanxx
 * @version $Id: WechatManager.java, v 0.1 2019年1月14日 下午1:51:32 yuanxx Exp $
 */
@Service(value = "wechatManager")
public class WechatManager {

    private Robot               robot;

    private final static Logger log = LoggerFactory.getLogger("SYS-LOG");

    /**
     * 
     * 注解：初始化机器人 
     * @return
     * @author yuanxx @date 2019年1月14日
     */
    public boolean initRobot() {

        try {
            ExecutorServiceUtils.getExcutorPools().execute(new Runnable() {
                @Override
                public void run() {
                    robot = new Robot(Config.me().autoLogin(false).showTerminal(true));
                    robot.start();
                }
            });
            return true;
        } catch (Exception e) {
            log.error("开启机器人线程失败", e);
            return false;
        }
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
        try {
            robot.loginOut();
            robot.setCanLogOut(true);
            return true;
        } catch (Exception e) {
            log.error("退出登录失败", e);
            return false;
        }
    }

    /**
     * 
     * 注解：机器人是否在运行
     * @return
     * @author yuanxx @date 2019年1月15日
     */
    public boolean isRunning() {
        return robot.isRunning();
    }

}
