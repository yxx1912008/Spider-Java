package cn.luckydeer.spider.manager.cat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.luckydeer.spider.common.cache.CacheData;
import cn.luckydeer.spider.common.constant.BaseConstants;
import cn.luckydeer.spider.common.enums.cat.WebCrawEnums;
import cn.luckydeer.spider.common.model.cat.banner.BannerModel;
import cn.luckydeer.spider.common.model.cat.goods.SearchGoodInfo;
import cn.luckydeer.spider.common.model.cat.wechat.WeixinPicTextItem;
import cn.luckydeer.spider.common.utils.date.DateUtilSelf;
import cn.luckydeer.spider.common.utils.email.AliyunEmail;
import cn.luckydeer.spider.common.utils.email.EmailOrder;
import cn.luckydeer.spider.common.utils.thread.ExecutorServiceUtils;
import cn.luckydeer.spider.common.view.response.ResponseObj;
import cn.luckydeer.spider.dao.cat.daoInterface.WxAppStatusDao;
import cn.luckydeer.spider.dao.cat.daoObject.WxAppStatusDo;
import cn.luckydeer.spider.dao.home.daoInterface.SysOptionsDao;
import cn.luckydeer.spider.dao.home.daoObject.SysOptionsDo;
import cn.luckydeer.spider.manager.api.WebCrawlApi;

import com.alibaba.fastjson.JSON;

/**
 * 购物猫管理
 * 
 * @author yuanxx
 * @version $Id: CatManager.java, v 0.1 2019年1月28日 下午3:57:41 yuanxx Exp $
 */
@Service
public class CatManager {

    private static final Logger           logger = LoggerFactory.getLogger("MANAGER-LOG");

    //缓存 
    private static Map<String, CacheData> cache  = new ConcurrentHashMap<String, CacheData>();

    @Autowired
    private WxAppStatusDao                wxAppStatusDao;

    @Autowired
    private SysOptionsDao                 sysOptionsDao;

    private WebCrawlApi                   webCrawlApi;

    /**
     * 
     * 注解：获取首页海报
     * @return
     * @author yuanxx @date 2018年8月27日
     */
    public ResponseObj getBanner() {
        List<BannerModel> list = WebCrawlApi.getBanner();
        return new ResponseObj(list);
    }

    /**
     * 
     * 注解：获取咚咚抢商品列表
     * @return
     * @author yuanxx @date 2018年8月27日
     */
    public String getDDongQ() {
        String result = WebCrawlApi.getDDongQ();
        if (StringUtils.isBlank(result)) {
            logger.error("获取抢购商品列表失败");
            return "获取信息失败";
        }
        return result;
    }

    /**
     * 
     * 注解：获取 9.9包邮商品列表
     * @param page
     * @return
     * @author yuanxx @date 2018年8月27日
     */
    public String getNine(String page) {

        String result = WebCrawlApi.getNine(page);
        System.out.println(result);
        if (StringUtils.isBlank(result)) {
            return "获取商品列表失败";
        }
        return result;
    }

    /**
     * 
     * 注解：获取 实时疯抢榜
     * @param catId
     * @return
     * @author yuanxx @date 2018年8月27日
     */
    public String getRealTime(Integer catId) {
        return WebCrawlApi.getRealTime(catId);
    }

    /**
     * 
     * 注解：搜索商品信息
     * @param keyWords
     * @return
     * @author yuanxx @date 2018年8月27日
     */
    public String searchGood(String keyWords) {
        return WebCrawlApi.searchGood(keyWords);
    }

    /**
     * 
     * 注解：获取商品详情
     * @param goodId
     * @return
     * @author yuanxx @date 2018年8月27日
     */
    public String getGoodDetail(String goodId) {
        return webCrawlApi.getGoodDetailNew(goodId);
    }

    /**
     * 
     * 注解：根据商品ID 获取商品 购买码
     * @param goodId
     * @return
     * @author yuanxx @date 2018年9月5日
     */
    public String getGoodCodeText(final HttpServletRequest request, final String goodId) {

        ExecutorServiceUtils.getExcutorPools().execute(new Runnable() {
            @Override
            public void run() {
                String ip = request.getRemoteAddr();
                EmailOrder emailOrder = new EmailOrder();
                emailOrder.setContent("有用户主动领取商品优惠券,时间:"
                                      + DateUtilSelf.simpleFormat(new Date())
                                      + "<br>用户的Ip地址为："
                                      + (StringUtils.equals(ip, "0:0:0:0:0:0:0:1") ? "127.0.0.1"
                                          : ip) + "<br>商品链接地址为:" + BaseConstants.IMPORT_BASE_URL
                                      + "r=p/d&id=" + goodId);
                emailOrder.setTitle("购物猫用户主动领取优惠券通知");
                emailOrder.setReceives(BaseConstants.EMAIL_RECEIVES);
                AliyunEmail.send(emailOrder);
            }
        });
        return WebCrawlApi.getGoodCodeText(goodId);
    }

    /**
     * 
     * 注解：获取领券直播商品列表
     * @param page
     * @return
     * @author yuanxx @date 2018年9月6日
     */
    public String getTicketLive(String page) {
        return WebCrawlApi.getTicketLive(page);
    }

    /**
     * 
     * 注解：获取正在抢商品列表
     * @return
     * @author yuanxx @date 2018年9月6日
     */
    public String getCurrentQiang() {
        return WebCrawlApi.getCurrentQiang();
    }

    /**
     * 
     * 注解：通过商品真实ID获取商品的主图信息
     * @param realGoodId
     * @return
     * @author yuanxx @date 2018年9月13日
     */
    public List<String> getGoodDescImg(String realGoodId) {
        return webCrawlApi.getGoodDescImgs(realGoodId);
    }

    /**
     * 
     * 注解：通过商品真实ID获取商品信息
     * @param realGoodId
     * @return
     * @author yuanxx @date 2018年9月17日
     */
    public String getGoodDetailByRealId(String realGoodId) {
        return webCrawlApi.getGoodDetailByRealId(realGoodId);
    }

    /**
     * 
     * 注解：搜索五个商品信息
     * @return
     * @author yuanxx @date 2018年9月28日
     */
    public List<WeixinPicTextItem> getSearchGoods(String keyWords) {

        String result = searchGood(keyWords);
        if (StringUtils.isNotBlank(result)) {
            //将搜索结果转换为数组
            List<SearchGoodInfo> goodList = JSON.parseArray(result, SearchGoodInfo.class);
            if (CollectionUtils.isEmpty(goodList)) {
                return null;
            }
            int fromIndex = 0;
            //如果搜索结果查过五条 ，就查询前五条 否则提取前几个
            int toIndex = goodList.size() > 5 ? 4 : goodList.size();
            List<SearchGoodInfo> topList = goodList.subList(fromIndex, toIndex);
            List<WeixinPicTextItem> resultList = new ArrayList<>();
            WeixinPicTextItem picTextItem = null;
            for (SearchGoodInfo searchGoodInfo : topList) {
                picTextItem = new WeixinPicTextItem();
                picTextItem.setDescription(searchGoodInfo.getQuan_jine() + "元优惠券，点击领取");
                picTextItem.setPicUrl(searchGoodInfo.getPic());
                picTextItem.setTitle(searchGoodInfo.getD_title());
                picTextItem.setUrl(BaseConstants.IMPORT_BASE_URL + "r=p/d&id="
                                   + searchGoodInfo.getGoodsid() + "&type=3");
                resultList.add(picTextItem);
            }
            return resultList;
        }
        return null;
    }

    /**
     * 
     * 注解：查询微信小程序状态
     * 使用缓存功能 缓存一天
     * @param versionId
     * @return
     * @author yuanxx @date 2018年9月18日
     */
    public WxAppStatusDo getWxAppStatus(String versionId) {
        String key = WebCrawEnums.WX_STATUS.getCode() + versionId;
        if (getCacheTimeOut(key)) {
            CacheData data = cache.get(key);
            return (WxAppStatusDo) data.getData();
        }
        WxAppStatusDo info = wxAppStatusDao.selectByPrimaryKey(versionId);

        if (null != info) {
            String apiUrl = getApiUrl();
            info.setBaseUrl(apiUrl);
            updateCache(info, key);
        }

        return info;
    }

    /**
     * 
     * 注解：购物猫伪装功能 查询代理商区域费用
     * @param areaName
     * @return
     * @author yuanxx @date 2018年9月21日
     */
    public String queryAgent(String areaName) {
        String urlString = "http://member.icaomei.com/acaomei/qufx/search.do?areaName=" + areaName;
        Response doc;
        try {
            doc = Jsoup.connect(urlString).execute();
            return doc.body();
        } catch (IOException e) {
            logger.error("网络请求失败：请求地址{}", urlString, e);
            return null;
        }
    }

    /**
     * 
     * 注解：判断缓存是否过期
     * 当前仅存放 一天的小程序状态缓存 
     * 省去 经常去数据库捞取
     * @param key
     * @return
     * @author yuanxx @date 2018年9月25日
     */
    private static boolean getCacheTimeOut(String key) {
        CacheData cahe = cache.get(key);
        if (null == cahe) {
            return false;
        }
        Date now = new Date();
        Date expiryTime = cahe.getExpiryTime();
        int count = DateUtilSelf.calculateDecreaseMinute(expiryTime, now);
        if (count > 1440) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 注解：更新缓存
     * @param data
     * @param key
     * @author yuanxx @date 2018年9月25日
     */
    private static void updateCache(Object data, String key) {
        Date date = new Date();
        Date expiryTime = DateUtilSelf.increaseHour(date, 24);
        CacheData cacheData = new CacheData();
        cacheData.setKey(key);
        cacheData.setExpiryTime(expiryTime);
        cacheData.setData(data);
        cache.put(key, cacheData);
    }

    /**
     * 
     * 注解：清空缓存
     * @author yuanxx @date 2018年9月26日
     */
    public void clearCache() {
        cache.clear();
        WebCrawlApi.clearCache();
    }

    /**
     * 
     * 注解：
     * @return
     * @author yuanxx @date 2019年1月26日
     */
    private String getApiUrl() {
        SysOptionsDo record = sysOptionsDao.selectByPrimaryKey(1);
        if (null == record) {
            return BaseConstants.WX_BASE_API_URL;
        }
        return record.getOptionValue();
    }

    public static void main(String[] args) throws Exception {

        String url = "https://api.douban.com/v2/movie/in_theaters?count=2";
        Response res = Jsoup.connect(url).ignoreContentType(true).execute();

        System.out.println(res.statusCode());
        System.out.println(res.body());

    }

}
