package cn.luckydeer.spider.common.model.tuling;

import java.io.Serializable;

/**
 * 
 * 
 * @author yuanxx
 * @version $Id: TuUserInfo.java, v 0.1 2019年1月15日 上午10:04:38 yuanxx Exp $
 */
public class TuUserInfo implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    private String            apiKey           = "1bf8ff5c84ec4e03bcbb06dfdfcd3475";

    private String            userId           = "f859d9ba86fe414ca8497cb0b2aae5fa";

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
