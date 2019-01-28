package cn.luckydeer.spider.common.enums.cat;

/**
 * 
 * 购物猫网页抓取
 * 
 * @author yuanxx
 * @version $Id: WebCrawEnums.java, v 0.1 2018年8月26日 下午5:33:35 yuanxx Exp $
 */
public enum WebCrawEnums {

    BANNER("BANNER", "首页海报"), 
    QIANGGOU("QIANGGOU", "正在抢购"),
    NINE("NINE", "9.9包邮"),
    REAL_TIME("REAL_TIME","实时疯抢榜"),
    NEW_QIANG("NEW_QIANG","新版抢购"),
    WX_STATUS("WX_STATUS","小程序开关状态")
    ;

    private String code;

    private String detail;

    WebCrawEnums(String code, String detail) {
        this.code = code;
        this.detail = detail;
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
