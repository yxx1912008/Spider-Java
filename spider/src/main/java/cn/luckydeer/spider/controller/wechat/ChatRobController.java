package cn.luckydeer.spider.controller.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.luckydeer.spider.common.view.response.ResponseObj;
import cn.luckydeer.spider.manager.wechat.WechatManager;

/**
 * 微信机器人控制
 * 
 * @author yuanxx
 * @version $Id: ChatRobController.java, v 0.1 2019年1月15日 下午2:35:41 yuanxx Exp $
 */
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
    public ResponseObj start() {
        boolean flag = wechatManager.initRobot();
        return new ResponseObj(flag);
    }

    /**
     * 
     * 注解：获取微信登录二维码
     * @return
     * @author yuanxx @date 2019年1月15日
     */
    @RequestMapping(value = "/getCode.do")
    public ResponseObj getCode() {
        return new ResponseObj(wechatManager.codeUrl());
    }

    /**
     * 
     * 注解：退出登录
     * @return
     * @author yuanxx @date 2019年1月14日
     */
    @RequestMapping(value = "/loginOut.do")
    public ResponseObj loginOut() {
        boolean flag = wechatManager.loginOut();
        return new ResponseObj(flag);
    }

    /**
     * 
     * 注解：机器人是否在运行
     * @return
     * @author yuanxx @date 2019年1月15日
     */
    @RequestMapping(value = "/isRunning.do")
    public ResponseObj isRunning() {
        return new ResponseObj(wechatManager.isRunning());
    }

}
