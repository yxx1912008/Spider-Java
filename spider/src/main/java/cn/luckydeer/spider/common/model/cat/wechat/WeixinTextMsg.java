package cn.luckydeer.spider.common.model.cat.wechat;

import java.io.Serializable;

/**
 * 
 * 微信 消息 纯文本
 * 
 * @author yuanxx
 * @version $Id: WeixinTextMsg.java, v 0.1 2018年9月28日 上午10:27:05 yuanxx Exp $
 */
public class WeixinTextMsg extends BaseWxMessage implements Serializable {

    /**  */
    private static final long serialVersionUID = 5834563163399852326L;

    //微信回复的消息内容
    private String            Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}
