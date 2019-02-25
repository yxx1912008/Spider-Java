package cn.luckydeer.spider.manager.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.luckydeer.spider.common.model.news.FengNews;
import cn.luckydeer.spider.common.utils.page.JsoupUtils;

/**
 * 抓取科技新闻Api
 * 
 * @author yuanxx
 * @version $Id: TechNewsApi.java, v 0.1 2019年2月25日 下午4:30:32 yuanxx Exp $
 */
public class TechNewsApi {

    private static final Logger logger   = LoggerFactory.getLogger("MANAGER-LOG");

    public static final String  FENG_URL = "http://news.feng.com/publish/content.php?id=616370&Custom1=2&Custom3=news";

    /**
     * 
     * 注解：获取科技新闻列表
     * @author yuanxx @date 2019年2月25日
     */
    public static List<FengNews> getTechNews(Integer page) {
        Map<String, String> params = new HashMap<>();
        params.put("page", page.toString());
        Document doc = JsoupUtils.getDocumentPost(FENG_URL, params);
        if (null == doc) {
            return null;
        }
        try {
            JSONObject result = JSON.parseObject(doc.text());
            List<FengNews> list = null;
            if (StringUtils.equals(result.getString("status"), "success")) {
                JSONArray resultList = result.getJSONArray("dataList");
                list = resultList.toJavaList(FengNews.class);
                return list;
            }
            return list;
        } catch (Exception e) {
            logger.error("获取新闻返回数据失败：{}", ToStringBuilder.reflectionToString(doc), e);
            return null;
        }
    }

}
