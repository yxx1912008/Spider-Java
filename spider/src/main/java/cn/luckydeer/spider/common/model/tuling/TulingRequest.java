package cn.luckydeer.spider.common.model.tuling;

import java.io.Serializable;

public class TulingRequest implements Serializable {

    /**  */
    private static final long serialVersionUID = 6339008639048805287L;

    private Integer           reqType          = 0;

    private TuPerception      perception;

    private TuUserInfo        userInfo;

    public Integer getReqType() {
        return reqType;
    }

    public void setReqType(Integer reqType) {
        this.reqType = reqType;
    }

    public TuPerception getPerception() {
        return perception;
    }

    public void setPerception(TuPerception perception) {
        this.perception = perception;
    }

    public TuUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(TuUserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
