package cn.luckydeer.spider.controller.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.luckydeer.spider.manager.wechat.WechatManager;

@RestController
@RequestMapping(value = "/test")
public class ChatRobController {

    @Autowired
    private WechatManager wechatManager;

    @RequestMapping(value = "/test.do")
    public String test() {
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

    @RequestMapping(value = "/test2.do")
    public String test2() {
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

}
