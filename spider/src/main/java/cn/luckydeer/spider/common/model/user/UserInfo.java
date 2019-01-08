package cn.luckydeer.spider.common.model.user;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    private String            uName;

    private String            password;

    /** 超时时间  */
    private Date              failTime;

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFailTime() {
        return failTime;
    }

    public void setFailTime(Date failTime) {
        this.failTime = failTime;
    }

}
