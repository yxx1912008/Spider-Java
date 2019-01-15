package cn.luckydeer.spider.manager.wechat;

import io.github.biezhi.wechat.WeChatBot;
import io.github.biezhi.wechat.api.annotation.Bind;
import io.github.biezhi.wechat.api.constant.Config;
import io.github.biezhi.wechat.api.enums.AccountType;
import io.github.biezhi.wechat.api.enums.MsgType;
import io.github.biezhi.wechat.api.model.WeChatMessage;
import io.github.biezhi.wechat.utils.StringUtils;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.luckydeer.spider.common.model.tuling.TuInputText;
import cn.luckydeer.spider.common.model.tuling.TuPerception;
import cn.luckydeer.spider.common.model.tuling.TuUserInfo;
import cn.luckydeer.spider.common.model.tuling.TulingRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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

    /**
     * 
     * 注解：监听群聊消息
     * @param message
     * @author yuanxx @date 2019年1月15日
     */
    @Bind(accountType = AccountType.TYPE_GROUP)
    public void groupMessage(WeChatMessage message) {
        if (StringUtils.isNotEmpty(message.getId())) {
            log.info("接收到群 [{}] 的消息: {}", message.getName(), message.getText());
            if (org.apache.commons.lang3.StringUtils.contains(message.getText(), "券达人")) {
                String keyWord = org.apache.commons.lang3.StringUtils.remove(message.getText(),
                    "券达人");
                String url = "http://openapi.tuling123.com/openapi/api/v2";
                TuInputText text = new TuInputText(keyWord);
                TulingRequest request = new TulingRequest();
                request.setPerception(new TuPerception(text));
                request.setReqType(0);
                request.setUserInfo(new TuUserInfo());
                String json = JSON.toJSONString(request);
                try {
                    Document res = Jsoup.connect(url).requestBody(json)
                        .header("Content-Type", "application/json").method(Method.POST).post();
                    JSONObject object = JSON.parseObject(res.text());
                    String result = object.getJSONArray("results").getJSONObject(0)
                        .getJSONObject("values").getString("text");
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(result)) {
                        this.sendMsg(message.getFromUserName(), result);
                    }
                } catch (Exception e) {
                    log.error("请求失败", e);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        String url = "http://openapi.tuling123.com/openapi/api/v2";
        TuInputText text = new TuInputText("你好");
        TulingRequest request = new TulingRequest();
        request.setPerception(new TuPerception(text));
        request.setReqType(0);
        request.setUserInfo(new TuUserInfo());
        String json = JSON.toJSONString(request);
        Document res = Jsoup.connect(url).requestBody(json)
            .header("Content-Type", "application/json").method(Method.POST).post();

        JSONObject object = JSON.parseObject(res.text());

        String result = object.getJSONArray("results").getJSONObject(0).getJSONObject("values")
            .getString("text");

        System.out.println(result);
    }
}
