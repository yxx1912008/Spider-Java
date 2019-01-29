package cn.luckydeer.spider.dao.home.daoInterface;

import org.springframework.stereotype.Repository;

import cn.luckydeer.spider.dao.home.daoObject.SysOptionsDo;

/**
 * 系统默认配置
 * 
 * @author yuanxx
 * @version $Id: SysOptionsDao.java, v 0.1 2019年1月28日 下午3:54:20 yuanxx Exp $
 */
@Repository
public interface SysOptionsDao {

    SysOptionsDo selectByPrimaryKey(Integer optionId);
}