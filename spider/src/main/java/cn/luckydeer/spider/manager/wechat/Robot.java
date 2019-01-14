package cn.luckydeer.spider.manager.wechat;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.biezhi.wechat.WeChatBot;
import io.github.biezhi.wechat.api.annotation.Bind;
import io.github.biezhi.wechat.api.constant.Config;
import io.github.biezhi.wechat.api.enums.AccountType;
import io.github.biezhi.wechat.api.enums.MsgType;
import io.github.biezhi.wechat.api.model.WeChatMessage;
import io.github.biezhi.wechat.utils.StringUtils;

/**
 * 
 * 微信机器人管理
 * @author yuanxx
 * @version $Id: WechatManager.java, v 0.1 2019年1月10日 下午6:07:17 yuanxx Exp $
 */
public class Robot extends WeChatBot {

    private final static Logger log = LoggerFactory.getLogger("SYS-LOG");

    public Robot(Config config) {
        super(config);
    }

    @Bind(msgType = MsgType.TEXT)
    public void handleText(WeChatMessage message) {

        if (StringUtils.isNotEmpty(message.getId())) {
            //log.info("接收到 [{}] 的消息: {}", message.getName(), message.getText());
            System.out.println("接收到:" + message.getName() + "内容：" + message.getText());
            this.sendMsg(message.getFromUserName(), "自动回复: " + message.getText());
        }
    }

}
