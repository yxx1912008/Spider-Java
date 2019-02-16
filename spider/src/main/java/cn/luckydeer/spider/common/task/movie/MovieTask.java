package cn.luckydeer.spider.common.task.movie;

import java.io.IOException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.luckydeer.spider.common.utils.date.DateUtilSelf;
import cn.luckydeer.spider.manager.movie.MovieManager;

/**
 * 
 * 
 * @author yuanxx
 * @version $Id: CatTask.java, v 0.1 2019年1月30日 下午4:56:05 yuanxx Exp $
 */
@Component
public class MovieTask {

    private final static Logger logger = LoggerFactory.getLogger("TASK-LOG");

    @Autowired
    private MovieManager        movieManager;

    /**
     * 
     * 注解：调度任务开始
     * 每三天的下午四点执行一次
     * 解除注释，则执行cron表达式定义的规则
     * @author yuanxx @date 2018年11月15日
     */
    @Scheduled(cron = "0 0 16 1/3 * ?")
    public void getAirtcleInfo() {
        logger.info("开始电影文章采集:{}", DateUtilSelf.dtSimpleChineseFormat(new Date()));
        try {
            movieManager.getAirtcleInfo();
            logger.info("采集结束");
        } catch (Exception e) {
            logger.error("采集失败", e);
        }
    }

    /**
     * 
     * 注解：调度任务开始
     * 每天晚上23点调用接口抓取视频数据 
     * 解除注释，则执行cron表达式定义的规则
     * @author yuanxx @date 2018年11月15日
     */
    @Scheduled(cron = "0 0 10,23 * * ?")
    public void getMovieApi() {
        logger.info("调用远程采集接口", DateUtilSelf.dtSimpleChineseFormat(new Date()));
        try {
            Jsoup.connect("http://luckydeer.51vip.biz/api.php/timming/index.html?name=qiangqiang")
                .timeout(5000).get();
        } catch (IOException e) {
            logger.error("抓取电影数据连接超时", e);
        }
    }

}
