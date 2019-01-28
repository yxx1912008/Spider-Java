package cn.luckydeer.spider.dao.movie.dataInterface;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.luckydeer.spider.dao.movie.dataObject.MacVodDo;

/**
 * 电影信息操作
 * 
 * @author yuanxx
 * @version $Id: IMacVodDao.java, v 0.1 2018年10月12日 下午2:40:38 yuanxx Exp $
 */
@Service
public interface MacVodDao {

    MacVodDo selectByPrimaryKey(Integer vodId);

    /**
     * 
     * 注解：获取最近更新前五的电影信息
     * @return
     * @author yuanxx @date 2018年10月12日
     */
    List<MacVodDo> selectTopFiveMovie(String keyWord);

}