package cn.luckydeer.spider.controller.test;

import java.io.IOException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.luckydeer.spider.common.constant.BaseConstants;
import cn.luckydeer.spider.common.memcached.DistributedCached;
import cn.luckydeer.spider.common.memcached.enums.CachedType;
import cn.luckydeer.spider.common.utils.date.DateUtilSelf;
import cn.luckydeer.spider.common.utils.email.AliyunEmail;
import cn.luckydeer.spider.common.utils.email.EmailOrder;
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

    @RequestMapping(value = "/testMail.do")
    public ResponseObj testMail() {
        logger.info("开始测试邮件");
        EmailOrder emailOrder = new EmailOrder();
        emailOrder.setContent("测试邮件~~~~");
        emailOrder.setTitle("订阅号文章模板:" + DateUtilSelf.sdtShortFormat(new Date()));
        emailOrder.setReceives(BaseConstants.EMAIL_RECEIVES);
        AliyunEmail.send(emailOrder);
        return new ResponseObj();
    }

    /**
     * 
     * 注解：获取树莓派服务器的外网IP
     * @return
     * @author yuanxx @date 2019年2月14日
     */
    @RequestMapping(value = "/getRealIp.do")
    public String getRealIp() {
        String url = "http://ifconfig.co/ip";
        Document res;
        try {
            res = Jsoup.connect(url).ignoreContentType(true).get();
            return res.text();
        } catch (IOException e) {
            logger.error("获取外网IP失败", e);
            return "获取外网IP失败";
        }
    }

}
