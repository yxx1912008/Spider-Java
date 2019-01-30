package cn.luckydeer.spider.mail;

import java.util.Date;

import org.junit.Test;

import cn.luckydeer.spider.SpiderApplicationTests;
import cn.luckydeer.spider.common.constant.BaseConstants;
import cn.luckydeer.spider.common.utils.date.DateUtilSelf;
import cn.luckydeer.spider.common.utils.email.AliyunEmail;
import cn.luckydeer.spider.common.utils.email.EmailOrder;

/**
 * 测试邮件发送
 * 
 * @author yuanxx
 * @version $Id: EmailTest.java, v 0.1 2019年1月30日 下午2:47:41 yuanxx Exp $
 */
public class EmailTest extends SpiderApplicationTests {

    @Test
    public void testName() throws Exception {
        EmailOrder emailOrder = new EmailOrder();
        emailOrder.setContent("测试邮件");
        emailOrder.setTitle("订阅号文章模板:" + DateUtilSelf.sdtShortFormat(new Date()));
        emailOrder.setReceives(BaseConstants.EMAIL_RECEIVES);
        AliyunEmail.send(emailOrder);

    }

}
