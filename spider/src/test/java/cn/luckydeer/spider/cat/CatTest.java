package cn.luckydeer.spider.cat;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

import cn.luckydeer.spider.SpiderApplicationTests;
import cn.luckydeer.spider.dao.cat.daoInterface.WxAppStatusDao;
import cn.luckydeer.spider.dao.cat.daoObject.WxAppStatusDo;

/**
 * 购物猫测试
 * 
 * @author yuanxx
 * @version $Id: CatTest.java, v 0.1 2019年1月28日 上午10:39:42 yuanxx Exp $
 */
public class CatTest extends SpiderApplicationTests {

    @Resource
    private WxAppStatusDao wxAppStatusDao;

    /**
     * 
     * 注解：
     * @throws Exception
     * @author yuanxx @date 2019年1月28日
     */
    @Test
    public void wx_status_selectByPrimaryKey() throws Exception {
        String versionId = "1.0.1";
        WxAppStatusDo info = wxAppStatusDao.selectByPrimaryKey(versionId);
        System.out.println(ToStringBuilder.reflectionToString(info));
    }

}
