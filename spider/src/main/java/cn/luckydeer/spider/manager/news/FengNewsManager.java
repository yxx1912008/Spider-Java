package cn.luckydeer.spider.manager.news;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.luckydeer.spider.common.model.news.FengNews;
import cn.luckydeer.spider.manager.api.TechNewsApi;

/**
 * 科技新闻管理
 * 
 * @author yuanxx
 * @version $Id: FengNewsManager.java, v 0.1 2019年2月25日 下午5:41:48 yuanxx Exp $
 */
@Service
public class FengNewsManager {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger("MANAGER-LOG");

    /**
     * 
     * 注解：获取科技新闻列表
     * @param page
     * @return
     * @author yuanxx @date 2019年2月25日
     */
    public List<FengNews> getTechNews(Integer page) {
        return TechNewsApi.getTechNews(page);
    }

}
