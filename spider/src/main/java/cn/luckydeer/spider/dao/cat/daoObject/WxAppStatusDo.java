package cn.luckydeer.spider.dao.cat.daoObject;

import java.io.Serializable;

/**
 * 购物猫小程序
 * 状态开关
 * 0.用于过审 展示虚假页面
 * 1.展示正常页面
 * 
 * @author yuanxx
 * @version $Id: WxAppStatus.java, v 0.1 2018年9月18日 下午9:36:23 yuanxx Exp $
 */
public class WxAppStatusDo implements Serializable {

    /**  */
    private static final long serialVersionUID = -5147257780909692215L;

    //小程序版本号码
    private String            versionId;

    private int               status;

    //请求根地址
    private String            baseUrl;

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

}
