package cn.luckydeer.spider.common.utils.email;

import java.io.Serializable;

/**
 * 邮件设置
 * 
 * @author yuanxx
 * @version $Id: EmailConfig.java, v 0.1 2018年9月27日 上午11:04:41 yuanxx Exp $
 */
public class EmailConfig implements Serializable {

    /**  */
    private static final long serialVersionUID = 5256784833290489204L;

    // 发送服务 : smtp.qq.com  smtp.exmail.qq.com
    protected String          host             = "smtp.mxhichina.com";

    //发件人账号
    protected String          sender           = "admin@luckydeer.cn";

    //服务器认证账号
    protected String          userName         = "admin@luckydeer.cn";

    //邮件认证服务密码
    protected String          password         = "Luckydeer2018";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
