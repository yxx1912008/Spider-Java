package cn.luckydeer.spider.common.model.wechat;

import java.io.Serializable;

/**
 * 
 * 微信AccessToken 
 * @author yuanxx
 * @version $Id: AccessToken.java, v 0.1 2018年10月12日 下午4:27:29 yuanxx Exp $
 */
public class AccessToken implements Serializable {
    private static final long serialVersionUID = -4917866183367343512L;

    private String            access_token;

    private String            expires_in;                               //失效时间

    private String            errcode;                                  //错误代码

    private String            errmsg;                                   //错误信息

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
