package cn.luckydeer.spider.common.cache;

import java.util.Date;

/**
 * 临时缓存相关类
 * 
 * @author yuanxx
 * @version $Id: CacheData.java, v 0.1 2018年8月26日 下午5:05:21 yuanxx Exp $
 */
public class CacheData {

    //缓存的Key
    private String key;

    //缓存的数据
    private Object data;

    //过期时间
    private Date   expiryTime;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

}
