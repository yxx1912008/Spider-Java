package cn.luckydeer.spider.manager.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.luckydeer.spider.common.cache.CaChePrefixConstants;
import cn.luckydeer.spider.common.cache.CacheData;
import cn.luckydeer.spider.common.constant.BaseConstants;
import cn.luckydeer.spider.common.enums.cat.WebCrawEnums;
import cn.luckydeer.spider.common.memcached.DistributedCached;
import cn.luckydeer.spider.common.memcached.enums.CachedType;
import cn.luckydeer.spider.common.model.cat.banner.BannerModel;
import cn.luckydeer.spider.common.utils.date.DateUtilSelf;
import cn.luckydeer.spider.common.utils.thread.ExecutorServiceUtils;
import cn.luckydeer.spider.dao.home.daoInterface.SysOptionsDao;
import cn.luckydeer.spider.dao.home.daoObject.SysOptionsDo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 获取海报
 * @author yuanxx
 * @version $Id: BannerApi.java, v 0.1 2018年8月24日 下午4:58:43 yuanxx Exp $
 */
@Service
public class WebCrawlApi {

    private static final Logger           logger       = LoggerFactory.getLogger("MANAGER-LOG");

    /** 网页抓取缓存 固定时间更新 避免多次抓取 节约资源  */
    private static Map<String, CacheData> webCrawCache = new ConcurrentHashMap<String, CacheData>();

    private static String                 nine_cac_id;                                              //9.9包邮 

    private static String                 live_cac_id;                                              //领券直播

    @Autowired
    private DistributedCached             distributedCached;

    @Autowired
    private SysOptionsDao                 sysOptionsDao;

    private static String                 baseUrl;

    /**
     * 
     * 注解：初始化时把主站地址放入内存
     * @author yuanxx @date 2019年1月29日
     */
    @PostConstruct
    private void init() {
        SysOptionsDo record = sysOptionsDao.selectByPrimaryKey(2);
        if (null == record) {
            return;
        }
        baseUrl = record.getOptionValue();
    }

    /**
     * 
     * 注解：获取首页海报
     * @author yuanxx @date 2018年8月24日
     */
    @SuppressWarnings("unchecked")
    public static List<BannerModel> getBanner() {
        String key = WebCrawEnums.BANNER.getCode();
        boolean flag = getCacheTimeOut(key);
        if (flag) {
            CacheData cache = webCrawCache.get(key);
            return (List<BannerModel>) cache.getData();
        }

        //请求海报信息
        Document doc;
        try {
            doc = Jsoup.connect(baseUrl).timeout(BaseConstants.DEFAULT_TIME_OUT).get();
            Elements elements = doc.getElementsByClass("swiper-slide");
            Iterator<Element> it = elements.iterator();
            BannerModel model = null;
            List<BannerModel> list = new ArrayList<>();
            while (it.hasNext()) {
                Element element = (Element) it.next();
                model = new BannerModel();
                String goodId = element.select("a").attr("data-gid");
                String imgUrl = element.select("img").attr("src");
                model.setBannerImg(imgUrl.trim());
                model.setGoodId(goodId);

                if (StringUtils.isBlank(goodId)) {
                    continue;

                }
                list.add(model);
            }
            updateCache(list, key);
            return list;
        } catch (Exception e) {
            logger.error("首页海报获取失败", e);
            return null;
        }
    }

    /**
     * 
     * 注解：判断缓存是否过期
     * @param key
     * @return
     * @author yuanxx @date 2018年8月26日
     */
    private static boolean getCacheTimeOut(String key) {
        CacheData cahe = webCrawCache.get(key);
        if (null == cahe) {
            return false;
        }
        Date now = new Date();
        Date expiryTime = cahe.getExpiryTime();
        int count = DateUtilSelf.calculateDecreaseMinute(expiryTime, now);
        if (count > BaseConstants.DEFAULT_CACHE_TIME) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 注解：更新缓存
     * @param data
     * @param key
     * @author yuanxx @date 2018年8月26日
     */
    private static void updateCache(Object data, String key) {
        Date date = new Date();
        Date expiryTime = DateUtilSelf.increaseHour(date, 2);
        CacheData cacheData = new CacheData();
        cacheData.setKey(key);
        cacheData.setExpiryTime(expiryTime);
        cacheData.setData(data);
        webCrawCache.put(key, cacheData);
    }

    /**
     * 
     * 注解：清除缓存
     * @author yuanxx @date 2018年9月26日
     */
    public static void clearCache() {
        webCrawCache.clear();
    }

    /**
     * 
     * 注解：获取咚咚抢商品列表
     * @return
     * @author yuanxx @date 2018年8月26日
     */
    public static String getDDongQ() {
        String key = WebCrawEnums.QIANGGOU.getCode();
        boolean flag = getCacheTimeOut(key);
        if (flag) {
            CacheData cache = webCrawCache.get(key);
            return (String) cache.getData();
        }
        Document doc;
        try {
            doc = Jsoup.connect(baseUrl + "r=ddq/wap").timeout(BaseConstants.DEFAULT_TIME_OUT)
                .get();
            String rexString = "data = (.*?);";
            Pattern pattern = Pattern.compile(rexString);
            Matcher m = pattern.matcher(doc.toString());
            if (m.find()) {
                String result = m.group(1).trim();
                updateCache(result, key);
                return result;
            }
            return null;
        } catch (IOException e) {
            logger.error("获取咚咚枪信息失败", e);
            return null;
        }
    }

    /**
     * 
     * 注解：获取9.9包邮商品列表
     * @param page
     * @return
     * @author yuanxx @date 2018年8月27日
     */
    public static String getNine(String page) {

        //拼接请求参数
        StringBuilder builder = new StringBuilder(baseUrl);
        builder.append("r=nine/listajax&n_id=58&page=").append(page).append("&cac_id=");
        if (StringUtils.isNotBlank(nine_cac_id)) {
            builder.append(nine_cac_id);
        }
        String url = builder.toString();
        try {
            Document doc = Jsoup.connect(url).timeout(BaseConstants.DEFAULT_TIME_OUT).get();
            String result = doc.text();
            if (StringUtils.equals("1", page)) {
                String str = JSON.parseObject(result).getJSONObject("data").getString("cac_id");
                nine_cac_id = str;
            }
            return result;
        } catch (IOException e) {
            logger.error("读取9.9包邮商品列表失败", e);
            return null;
        }
    }

    /**
     * 
     * 注解：获取实时疯抢榜
     * @param catId
     * @return
     * @author yuanxx @date 2018年8月27日
     */
    public static String getRealTime(Integer catId) {

        if (null == catId) {
            catId = 0;
        }
        String key = WebCrawEnums.REAL_TIME.getCode() + catId;
        boolean flag = getCacheTimeOut(key);

        if (flag) {
            CacheData cache = webCrawCache.get(key);
            return (String) cache.getData();
        }
        String url = baseUrl + "r=realtime/wapajax&cid=" + catId;
        try {
            Document doc = Jsoup.connect(url).timeout(BaseConstants.DEFAULT_TIME_OUT).get();
            String result = doc.text();
            updateCache(result, key);
            return result;
        } catch (IOException e) {
            logger.error("获取实时疯抢榜失败", e);
            return null;
        }
    }

    /**
     * 
     * 注解：搜索 全网优惠券商品
     * @param type
     * @param keyWords
     * @return
     * @author yuanxx @date 2018年8月27日
     */
    public static String searchGood(String keyWords) {

        StringBuilder builder = new StringBuilder(baseUrl);
        builder.append("r=index%2Fsearch&s_type=1&kw=");
        builder.append(keyWords);
        try {
            String url = builder.toString();
            Document doc = Jsoup.connect(url).timeout(BaseConstants.DEFAULT_TIME_OUT).get();
            //正则匹配规则
            String regex = "dtk_data=(.*?);";
            Pattern pattern = Pattern.compile(regex);
            Matcher m = pattern.matcher(doc.html());
            if (m.find()) {
                return m.group(1).trim();
            }
            logger.error("搜索结果转换失败");
            return null;
        } catch (IOException e) {
            logger.error("搜索商品失败", e);
            return null;
        }
    }

    /**
     * 
     * 注解：获取商品详情 新版接口 
     * 功能更全面 此前接口暂时废弃
     * @param goodId
     * @return
     * @author yuanxx @date 2018年9月13日
     */
    public String getGoodDetailNew(String goodId) {

        final Document doc;
        try {
            doc = Jsoup.connect(BaseConstants.IMPORT_BASE_URL + "r=p/d&id=" + goodId)
                .timeout(BaseConstants.DEFAULT_TIME_OUT).get();
            Elements elements = doc.getElementsByClass("info col-mar");
            Element element = elements.get(0);
            String shopIcon = element.getElementsByTag("img").attr("data-original");//店铺图标
            String shopName = element.getElementsByTag("h3").text();//店铺名称
            String rexString = "goodsItem = (.*?);";
            Pattern pattern = Pattern.compile(rexString);
            Matcher m = pattern.matcher(doc.toString());
            if (m.find()) {

                final JSONObject jsonObject = JSON.parseObject(m.group(1).trim());
                jsonObject.put("shopName", shopName);
                jsonObject.put("shopIcon", shopIcon);

                Elements imgListDiv = doc.getElementsByClass("imglist").get(0)
                    .getElementsByTag("img");
                List<String> list = new ArrayList<>();
                String head = "https:";
                if (!imgListDiv.isEmpty()) {
                    for (Element element2 : imgListDiv) {
                        list.add(head + element2.attr("data-original"));
                    }
                    String key = CaChePrefixConstants.GOOD_IMG_CACHE
                                 + jsonObject.getString("goodsid");
                    distributedCached.put(CachedType.BUSINESS_CACHE, key, list);
                }
                return jsonObject.toJSONString();
            }
            return null;
        } catch (IOException e) {
            logger.error("获取商品详情失败", e);
            return null;
        }
    }

    /**
     * 
     * 注解：通过商品真实ID获取商品的信息
     * @param realGoodId
     * @return
     * @author yuanxx @date 2018年9月17日
     */
    public String getGoodDetailByRealId(String realGoodId) {
        final Document doc;
        try {
            doc = Jsoup
                .connect(BaseConstants.IMPORT_BASE_URL + "r=p/d&id=" + realGoodId + "&type=3")
                .timeout(BaseConstants.DEFAULT_TIME_OUT).get();
            String rexString = "goodsItem = (.*?);";
            Pattern pattern = Pattern.compile(rexString);
            Matcher m = pattern.matcher(doc.toString());
            if (m.find()) {
                final String result = m.group(1).trim();
                ExecutorServiceUtils.getExcutorPools().execute(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject jsonObject = JSONObject.parseObject(result);
                        Elements imgListDiv = doc.getElementsByClass("imglist").get(0)
                            .getElementsByTag("img");
                        List<String> list = new ArrayList<>();
                        String head = "https:";
                        if (!imgListDiv.isEmpty()) {
                            for (Element element2 : imgListDiv) {
                                list.add(head + element2.attr("data-original"));
                            }
                            String key = CaChePrefixConstants.GOOD_IMG_CACHE
                                         + jsonObject.getString("goodsid");
                            distributedCached.put(CachedType.BUSINESS_CACHE, key, list);
                        }
                        logger.error("获取商品主图信息失败");
                    }
                });
                return result;
            }
            return null;
        } catch (IOException e) {
            logger.error("获取商品详情失败", e);
            return null;
        }
    }

    /**
     * 
     * 注解：获取商品 复制码
     * @param goodId
     * @return
     * @author yuanxx @date 2018年9月5日
     */
    public static String getGoodCodeText(String goodId) {

        if (StringUtils.isBlank(goodId)) {
            return null;
        }
        StringBuilder builder = new StringBuilder(BaseConstants.IMPORT_BASE_URL);
        builder.append("r=p/d&id=").append(goodId);
        String url = builder.toString();
        Document doc;
        try {
            doc = Jsoup.connect(url).timeout(BaseConstants.DEFAULT_TIME_OUT).get();
            Element element = doc.getElementById("codeText");
            return element.text();
        } catch (IOException e) {
            logger.error("获取商品购买码失败", e);
            return null;
        }
    }

    /**
     * 
     * 注解：获取领券直播商品列表
     * @param page
     * @return
     * @author yuanxx @date 2018年9月6日
     */
    public static String getTicketLive(String page) {

        //拼接请求参数
        StringBuilder builder = new StringBuilder(baseUrl);
        if (StringUtils.equals("1", page)) {
            live_cac_id = "";
        }
        builder.append("r=index/ajaxnew&page=").append(page).append("&cac_id=");
        if (StringUtils.isNotBlank(live_cac_id)) {
            builder.append(live_cac_id);
        }
        String url = builder.toString();
        try {
            Response doc = Jsoup.connect(url).timeout(BaseConstants.DEFAULT_TIME_OUT).execute();
            String result = doc.body();
            if (StringUtils.equals("1", page)) {
                String str = JSON.parseObject(result).getJSONObject("data").getString("cac_id");
                live_cac_id = str;
            }
            return result;
        } catch (IOException e) {
            logger.error("读取领券直播商品列表失败", e);
            return null;
        }
    }

    /**
     * 
     * 注解：获取正在抢商品列表
     * @return
     * @author yuanxx @date 2018年9月6日
     */
    public static String getCurrentQiang() {

        String key = WebCrawEnums.NEW_QIANG.getCode();
        boolean flag = getCacheTimeOut(key);
        if (flag) {
            CacheData cache = webCrawCache.get(key);
            return (String) cache.getData();
        }
        Document doc;
        try {
            doc = Jsoup.connect(baseUrl + "r=index/wap").timeout(BaseConstants.DEFAULT_TIME_OUT)
                .get();
            //使用正则匹配 （非贪婪模式）
            String rexString = "indexWillBring\",\"data\":(.*?),\"mta_name\"";
            Pattern pattern = Pattern.compile(rexString);
            Matcher m = pattern.matcher(doc.html());

            if (m.find()) {
                //直接通过正则表达式 提取 每日必买栏目，相比于用循环 ，这个速度更快 ，但是有局限性
                String resultString = m.group(1).trim();
                if (StringUtils.isNotBlank(resultString)) {
                    JSONArray willBringList = JSONObject.parseObject(resultString)
                        .getJSONObject("config").getJSONArray("list");
                    Iterator<Object> it = willBringList.iterator();
                    JSONArray resultList = new JSONArray();
                    while (it.hasNext()) {
                        JSONObject goodInfo = (JSONObject) it.next();
                        BigDecimal quanOver = goodInfo.getBigDecimal("quan_over");
                        BigDecimal yuanjia = goodInfo.getBigDecimal("yuanjia");
                        BigDecimal quanJine = goodInfo.getBigDecimal("quan_jine");
                        if (quanOver.compareTo(new BigDecimal("10000")) >= 0) {
                            goodInfo.put(
                                "quan_over",
                                quanOver.divide(new BigDecimal("10000"), 2,
                                    BigDecimal.ROUND_HALF_UP) + "万");
                        } else {
                            goodInfo.put("quan_over", quanOver);
                        }
                        goodInfo.put("nowPrice",
                            yuanjia.subtract(quanJine).setScale(2, BigDecimal.ROUND_HALF_UP));
                        resultList.add(goodInfo);
                    }
                    updateCache(resultList.toString(), key);
                    return resultList.toString();
                }
            }
            return null;
        } catch (Exception e) {
            logger.error("获取正在抢购商品信息失败", e);
            return null;
        }

    }

    /**
     * 
     * 注解：新版本获取主图信息是 
     * @param realGoodId
     * @return
     * @author yuanxx @date 2018年10月8日
     */
    public List<String> getGoodDescImgs(String realGoodId) {
        String key = CaChePrefixConstants.GOOD_IMG_CACHE + realGoodId;
        @SuppressWarnings("unchecked")
        List<String> list = (List<String>) distributedCached.get(CachedType.BUSINESS_CACHE, key);
        return list;
    }

    public static void main(String[] args) throws Exception {

        String goodId = "18226643";
        WebCrawlApi webCrawlApi = new WebCrawlApi();
        System.out.println(webCrawlApi.getGoodDetailNew(goodId));
    }

}
