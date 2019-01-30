package cn.luckydeer.spider.common.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 测试调度任务
 * 
 * @author yuanxx
 * @version $Id: TestTask.java, v 0.1 2018年11月15日 上午10:04:26 yuanxx Exp $
 */
@Component
public class TestTask {

    private final static Logger logger = LoggerFactory.getLogger("TASK-LOG");

    /**
     * 
     * 注解：调度任务开始
     * 解除注释，则执行cron表达式定义的规则
     * @author yuanxx @date 2018年11月15日
     */
    //@Scheduled(cron = "0 0/1 * * * ? ")
    public void run() {
        logger.info("调度任务开始");
    }

}
