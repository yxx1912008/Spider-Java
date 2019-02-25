package cn.luckydeer.spider.common.constant;

/**
 * Jsoup 请求通用参数
 * 
 * @author yuanxx
 * @version $Id: JsoupConstants.java, v 0.1 2019年2月25日 下午4:50:07 yuanxx Exp $
 */
public interface JsoupConstants {

    /**  最大重试次数 */
    public Integer MAX_RETRY  = 3;

    /**  单次重试休息时长 */
    public Integer SLEEP_TIME = 100;

}
