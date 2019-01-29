package cn.luckydeer.spider.common.enums.wechat;

import org.apache.commons.lang3.StringUtils;


/**
 * 微信消息类型
 * 
 * @author yuanxx
 * @version $Id: WeixinMsgType.java, v 0.1 2018年9月28日 上午9:41:31 yuanxx Exp $
 */
public enum WeixinMsgType {

    EVENT("event", "微信事件"), TEXT("text", "微信回复消息"), NEWS("news", "图文类型消息");

    WeixinMsgType(String code, String detail) {
        this.setCode(code);
        this.setDetail(detail);
    }

    private String code;

    private String detail;

    public static WeixinMsgType getEnumByCode(String code) {

        if (StringUtils.isNotBlank(code)) {
            for (WeixinMsgType type : WeixinMsgType.values()) {
                if (StringUtils.equals(type.getCode(), code)) {
                    return type;
                }
            }
        }
        return null;
    }

    public static String getDetailByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (WeixinMsgType type : WeixinMsgType.values()) {
                if (StringUtils.equals(code, type.getCode())) {
                    return type.getDetail();
                }
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
