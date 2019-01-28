package cn.luckydeer.spider.movie;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

import cn.luckydeer.spider.SpiderApplicationTests;
import cn.luckydeer.spider.dao.movie.dataInterface.MacVodDao;
import cn.luckydeer.spider.dao.movie.dataObject.MacVodDo;

/**
 * 电影资源数据库测试
 * 
 * @author yuanxx
 * @version $Id: MovieTest.java, v 0.1 2019年1月28日 下午2:00:17 yuanxx Exp $
 */
public class MovieTest extends SpiderApplicationTests {

    @Resource
    private MacVodDao macVodDao;

    /**
     * 
     * 注解：搜索排行前五的电影
     * @throws Exception
     * @author yuanxx @date 2019年1月28日
     */
    @Test
    public void selectTopFiveMovie() throws Exception {

        List<MacVodDo> info = macVodDao.selectTopFiveMovie(null);

        info.forEach(temp -> {
            System.out.println(ToStringBuilder.reflectionToString(temp));
        });

    }

}
