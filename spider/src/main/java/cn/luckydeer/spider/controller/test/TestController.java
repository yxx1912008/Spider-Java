package cn.luckydeer.spider.controller.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.luckydeer.spider.common.memcached.DistributedCached;
import cn.luckydeer.spider.common.memcached.enums.CachedType;
import cn.luckydeer.spider.common.view.response.ResponseObj;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    private final static Logger logger = LoggerFactory.getLogger("MEMCACHED-LOG");

    @Autowired
    private DistributedCached   distributedCached;

    /**
     * 
     * 注解：测试memcached
     * @return
     * @author yuanxx @date 2019年1月28日
     */
    @RequestMapping(value = "/testMemcached.do")
    public ResponseObj testMemlog() {
        logger.info("开始测试Memcached缓存");
        distributedCached.put(CachedType.BUSINESS_CACHE, "test", "001");
        logger.info("数据存放成功");
        String record = (String) distributedCached.get(CachedType.BUSINESS_CACHE, "test");
        logger.info("取出的数据是：{}", record);
        return new ResponseObj();
    }

}
