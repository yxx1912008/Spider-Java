/**
 * winchance Inc.
 * Copyright (c) 2010-2014 All Rights Reserved.
 */
package cn.luckydeer.spider.common.utils.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程上下文切换是有代价的
 * 由系统内存、线程量决定的公共线程池
 * @author yuanxx
 * @version $Id: ExecutorServiceUtils.java, v 0.1 2016年7月5日 上午11:29:19 panwuhai Exp $
 */
public class ExecutorServiceUtils {

    //     prod 4核cpu  
    //     如果是CPU（计算）密集型应用，则线程池大小设置为N+1
    //     如果是IO（网络）密集型应用，则线程池大小设置为2N+1
    private final static int       DEFAULT_THREAD_NUM = 9;

    /**
     * 公共线程池(不能被关闭，自动回收)
     */
    private static ExecutorService executorService;

    /**
     * 私有化默认构造器
     */
    private ExecutorServiceUtils() {

    }

    /**
     * 获取系统限制线程池
     * @return
     */
    public static ExecutorService getExcutorPools() {

        if (null == executorService) {

            synchronized (ExecutorServiceUtils.class) {

                if (null == executorService) {

                    executorService = Executors.newFixedThreadPool(DEFAULT_THREAD_NUM);

                }
            }

        }

        return executorService;
    }
}
