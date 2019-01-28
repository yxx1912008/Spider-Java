package cn.luckydeer.spider.common.model.cat.wechat;

import java.io.Serializable;

/**
 * 基础微信消息 
 * 
 * @author yuanxx
 * @version $Id: BaseMessage.java, v 0.1 2018年9月27日 下午4:52:28 yuanxx Exp $
 */
public class BaseWxMessage implements Serializable {
    /**  */
    private static final long serialVersionUID = 1599211644818910530L;

    // 开发者 微信号
    private String            ToUserName;

    //发送方帐号（一个OpenID）
    private String            FromUserName;

    //消息创建时间 （整型）
    private long              CreateTime;

    //  text 消息类型
    private String            MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

}
