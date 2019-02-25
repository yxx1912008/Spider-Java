package cn.luckydeer.spider.common.utils.page;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import cn.luckydeer.spider.common.constant.BaseConstants;
import cn.luckydeer.spider.common.constant.JsoupConstants;
import cn.luckydeer.spider.common.utils.valid.ValidateUtil;

/**
 * 数据爬取工具类，增加了 失败重连
 * 
 * @author yuanxx
 * @version $Id: JsoupUtils.java, v 0.1 2019年2月25日 下午4:40:57 yuanxx Exp $
 */
public class JsoupUtils {

    private static final Logger logger = LoggerFactory.getLogger("MANAGER-LOG");

    /**
     * 
     * 注解：Jsoup 工具类 
     * GET 方式
     * 支持重试请求数据
     * 会效验是否是网址，会效验网址是否为空
     * @param url
     * @author yuanxx @date 2019年2月25日
     * @throws InterruptedException 
     */
    public static Document getDocument(String url) {

        if (StringUtils.isBlank(url)) {
            logger.error("请求的Url地址不能为空");
            return null;
        }
        if (!ValidateUtil.isUrl(url)) {
            logger.error("链接格式有误：" + url);
            return null;
        }
        /**  最大重试次数 */
        int maxRetry = JsoupConstants.MAX_RETRY;
        /** 重试休眠时长  */
        int sleepTime = JsoupConstants.SLEEP_TIME;

        for (int i = 1; i <= maxRetry; i++) {
            try {
                /**  重试休眠  */
                if (i != 1) {
                    Thread.sleep(sleepTime);
                }
                Document doc = Jsoup.connect(url).timeout(BaseConstants.DEFAULT_TIME_OUT).get();
                return doc;
            } catch (InterruptedException e) {
                logger.error("线程休眠错误：url=" + url, e);
            } catch (Exception e) {
                logger.error("第{}次请求失败：url={}", i, url, e);
                continue;
            }
        }
        return null;
    }

    /**
     * 
     * 注解：Jsoup 工具类 
     * POST 方式
     * 支持重试请求数据
     * 会效验是否是网址，会效验网址是否为空
     * @param url
     * @author yuanxx @date 2019年2月25日
     * @throws InterruptedException 
     */
    public static Document getDocumentPost(String url, Map<String, String> params) {

        if (StringUtils.isBlank(url)) {
            logger.error("请求的Url地址不能为空");
            return null;
        }
        if (!ValidateUtil.isUrl(url)) {
            logger.error("链接格式有误：" + url);
            return null;
        }
        /**  最大重试次数 */
        int maxRetry = JsoupConstants.MAX_RETRY;
        /** 重试休眠时长  */
        int sleepTime = JsoupConstants.SLEEP_TIME;

        for (int i = 1; i <= maxRetry; i++) {
            try {
                /**  重试休眠  */
                if (i != 1) {
                    Thread.sleep(sleepTime);
                }
                Document doc = null;
                if (CollectionUtils.isEmpty(params)) {
                    doc = Jsoup.connect(url).timeout(BaseConstants.DEFAULT_TIME_OUT).post();
                } else {
                    doc = Jsoup.connect(url).data(params).timeout(BaseConstants.DEFAULT_TIME_OUT)
                        .get();
                }
                return doc;
            } catch (InterruptedException e) {
                logger.error("线程休眠错误：url=" + url, e);
            } catch (Exception e) {
                logger.error("第{}次请求失败：url={}", i, url, e);
                continue;
            }
        }
        return null;
    }

}
