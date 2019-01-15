package cn.luckydeer.spider.controller.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.luckydeer.spider.manager.wechat.WechatManager;

@RestController
@RequestMapping(value = "/chatRob", method = RequestMethod.POST)
public class ChatRobController {

    @Autowired
    private WechatManager wechatManager;

    /**
     * 
     * 注解：开启微信机器人
     * @return
     * @author yuanxx @date 2019年1月15日
     */
    @RequestMapping(value = "/start.do")
    public String start() {
        wechatManager.initRobot();
        Thread myThread = new Thread(new Runnable() {

            @Override
            public void run() {
                wechatManager.startRob();
            }
        });
        myThread.start();
        return "true";
    }

    /**
     * 
     * 注解：获取微信登录二维码
     * @return
     * @author yuanxx @date 2019年1月15日
     */
    @RequestMapping(value = "/getCode.do")
    public String getCode() {
        return wechatManager.codeUrl();
    }

    /**
     * 
     * 注解：退出登录
     * @return
     * @author yuanxx @date 2019年1月14日
     */
    @RequestMapping(value = "/loginOut.do")
    public boolean loginOut() {
        return wechatManager.loginOut();
    }

    /**
     * 
     * 注解：机器人是否在运行
     * @return
     * @author yuanxx @date 2019年1月15日
     */
    @RequestMapping(value = "/isRunning.do")
    public boolean isRunning() {
        return wechatManager.isRunning();
    }

}
