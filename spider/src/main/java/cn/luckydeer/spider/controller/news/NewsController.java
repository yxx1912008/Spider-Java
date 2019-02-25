package cn.luckydeer.spider.controller.news;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.luckydeer.spider.common.model.news.FengNews;
import cn.luckydeer.spider.common.view.response.ResponseObj;
import cn.luckydeer.spider.common.view.show.ViewShowEnums;
import cn.luckydeer.spider.manager.news.FengNewsManager;

/**
 * 新闻资讯 类
 * 
 * @author yuanxx
 * @version $Id: NewsController.java, v 0.1 2019年2月25日 下午5:44:34 yuanxx Exp $
 */
@RestController
@RequestMapping(value = "/news/api", method = RequestMethod.POST)
public class NewsController {

    @Autowired
    private FengNewsManager fengNewsManager;

    /**
     * 
     * 注解：获取科技新闻列表
     * @param request
     * @param response
     * @return
     * @author yuanxx @date 2018年8月24日
     */
    @RequestMapping(value = "/getTechNews.do", produces = { "application/json;charset=UTF-8" })
    public ResponseObj getIndexBanner(Integer page, HttpServletRequest request,
                                      HttpServletResponse response) {

        if (null == page || page < 0) {
            page = 0;
        }
        List<FengNews> list = fengNewsManager.getTechNews(page);

        if (CollectionUtils.isEmpty(list)) {
            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "获取新闻失败");
        }
        return new ResponseObj(list);
    }
}
