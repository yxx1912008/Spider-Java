package cn.luckydeer.spider.common.task.cat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.luckydeer.spider.manager.api.WebCrawlApi;

/**
 * 
 * 
 * @author yuanxx
 * @version $Id: CatTask.java, v 0.1 2019年1月30日 下午4:56:05 yuanxx Exp $
 */
@Component
public class CatTask {

    private final static Logger logger = LoggerFactory.getLogger("TASK-LOG");

    /**
     * 
     * 注解：调度任务开始
     * 每两个小时更新一次
     * 解除注释，则执行cron表达式定义的规则
     * @author yuanxx @date 2018年11月15日
     */
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void run() {
        logger.info("开始启动更新购物猫缓存的定时任务");
        WebCrawlApi.getBanner();//更新海报
        WebCrawlApi.getTicketLive("1");
        WebCrawlApi.getCurrentQiang();
        logger.info("更新缓存任务结束");
    }

}
