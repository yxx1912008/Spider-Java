package cn.luckydeer.spider.common.model.wechat;

import java.io.Serializable;

/**
 * 微信请求返回消息
 * 
 * @author yuanxx
 * @version $Id: BaseWeixinDto.java, v 0.1 2018年9月26日 下午5:03:24 yuanxx Exp $
 */
public class BaseWeixinDto implements Serializable {

    private static final long serialVersionUID = -4917866183367343513L;

    private String            errcode;                                  //错误码

    private String            errmsg;                                   //错误提示信息

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

}
