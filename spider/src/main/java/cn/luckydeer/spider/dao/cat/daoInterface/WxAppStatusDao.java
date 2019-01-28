package cn.luckydeer.spider.dao.cat.daoInterface;

import org.springframework.stereotype.Service;

import cn.luckydeer.spider.dao.cat.daoObject.WxAppStatusDo;

/**
 * 
 * 微信小程序状态
 * @author yuanxx
 * @version $Id: IWxAppStatusDao.java, v 0.1 2019年1月28日 上午10:37:34 yuanxx Exp $
 */
@Service
public interface WxAppStatusDao {

    int deleteByPrimaryKey(String versionId);

    int insert(WxAppStatusDo record);

    int insertSelective(WxAppStatusDo record);

    WxAppStatusDo selectByPrimaryKey(String versionId);

    int updateByPrimaryKeySelective(WxAppStatusDo record);

    int updateByPrimaryKey(WxAppStatusDo record);
}