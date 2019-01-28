package cn.luckydeer.spider.home;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

import cn.luckydeer.spider.SpiderApplicationTests;
import cn.luckydeer.spider.dao.home.daoInterface.SysOptionsDao;
import cn.luckydeer.spider.dao.home.daoObject.SysOptionsDo;

/**
 * 
 * 
 * @author yuanxx
 * @version $Id: HomeTest.java, v 0.1 2019年1月28日 下午4:34:49 yuanxx Exp $
 */
public class HomeTest extends SpiderApplicationTests {

    @Resource
    private SysOptionsDao sysOptionsDao;

    @Test
    public void sysOptionsDao_selectByPrimaryKey() throws Exception {

        Integer optionId = 1;
        SysOptionsDo ingo = sysOptionsDao.selectByPrimaryKey(optionId);
        System.out.println(ToStringBuilder.reflectionToString(ingo));
    }

}
