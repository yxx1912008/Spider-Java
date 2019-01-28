package cn.luckydeer.spider.manager.movie;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.luckydeer.spider.common.constant.BaseConstants;
import cn.luckydeer.spider.common.model.cat.wechat.WeixinPicTextItem;
import cn.luckydeer.spider.common.utils.date.DateUtilSelf;
import cn.luckydeer.spider.common.utils.email.AliyunEmail;
import cn.luckydeer.spider.common.utils.email.EmailOrder;
import cn.luckydeer.spider.dao.movie.dataInterface.MacVodDao;
import cn.luckydeer.spider.dao.movie.dataObject.MacVodDo;

/**
 * 
 * 
 * @author yuanxx
 * @version $Id: MovieManager.java, v 0.1 2019年1月28日 下午4:46:29 yuanxx Exp $
 */
@Service
public class MovieManager {

    @Autowired
    private MacVodDao           macVodDao;

    private static final Logger logger = LoggerFactory.getLogger("MANAGER-LOG");

    /**
     * 
     * 注解：查询五条电影信息
     * @return
     * @author yuanxx @date 2018年10月12日
     */
    private List<MacVodDo> selectTopFiveMovie(String keyWords) {
        return macVodDao.selectTopFiveMovie(keyWords);
    }

    /**
     * 
     * 注解：搜索电影信息
     * @param keyWord
     * @param fName
     * @param toName
     * @return
     * @author yuanxx @date 2018年9月28日
     */
    public List<WeixinPicTextItem> getMovieInfo(String keyWord, String fName, String toName) {

        List<WeixinPicTextItem> list = new ArrayList<>();
        List<MacVodDo> movieList = selectTopFiveMovie(keyWord);
        String picUrl = BaseConstants.BASE_LOGO_URL;
        if (!CollectionUtils.isEmpty(movieList)) {
            //因为微信后台修改自动回复消息策略，故自动回复只显示一条消息
            picUrl = movieList.get(0).getVodPic();
        }

        WeixinPicTextItem picTextItem = new WeixinPicTextItem();
        picTextItem.setTitle("影视《" + keyWord + "》已经找到，点击查看更多信息");
        picTextItem.setPicUrl(picUrl);
        picTextItem.setDescription("查看更多电影信息");

        //拼接电影搜索网址
        String urlString;
        try {
            urlString = BaseConstants.MOVIE_URL + "/index.php/vod/search.html?wd="
                        + URLEncoder.encode(keyWord, "UTF-8");
            picTextItem.setUrl(urlString);
            list.add(picTextItem);
            return list;
        } catch (UnsupportedEncodingException e) {
            logger.error("搜索电影失败", e);
            return null;
        }
    }

    /**
     * 
     * 注解：从数据库捞取五条电影信息 
     * 自动生成 订阅号文章 
     * 并发送邮件 到运营邮箱
     * @author yuanxx @date 2018年10月22日
     */
    public void getAirtcleInfo() {
        List<MacVodDo> list = macVodDao.selectTopFiveMovie(null);

        StringBuilder builder = new StringBuilder(
            "<section data-role=\"outer\" label=\"Powered by 135editor.com\" style=\"font-size:16px;font-family:微软雅黑;\"><p><br /></p><section class=\"_135editor\" style=\"border: 0px none; box-sizing: border-box;\"><section class=\"layout\" style=\"margin: 10px auto; padding: 15px 5px; width: 60%; border-top: 1px solid rgb(191,191, 191); border-left-width: 0px; border-bottom: 1px solid rgb(191, 191, 191); line-height: 25px; text-align:center; border-right-color: rgb(191, 191, 191); border-left-color: rgb(191, 191, 191); font-size: 18px; color:rgb(242, 100, 124); box-sizing: border-box;\"><p>周末狂欢，怎能少了这部电影？</p></section></section><p><br /></p><p style=\"text-align: left; font-size: 14px; line-height: 1.8em; color: rgb(161, 161, 161); letter-spacing: 0px;text-indent: 2em; margin-top: 5px;\"><span style=\"font-size: 19px;\"><strong>非常感谢大家关注猫猫君&nbsp;，本周猫猫君又为大家更新了几部新的电影，现在让我们快来看一下有哪些新片吧~</strong><strong><img src=\"http://img.baidu.com/hi/babycat/C_0004.gif\" style=\"white-space: normal;\" /></strong></span></p><p style=\"text-align: center; margin-top: 10px;\"><img data-width=\"100%\" class=\"lazy\" src=\"https://image2.135editor.com/cache/remote/aHR0cHM6Ly9tbWJpei5xbG9nby5jbi9tbWJpel9wbmcvWVV5WjdBT0wzb2wyUGJHNU9mVDlrWGN1SkJxZTNMUElWbW5NSjNlMWJrTnNZNnlZUDRlNmJDaWJWRGFxemx2aWMxYkR1VUFGbkFjWW1QQUVSbEdwYnIydy8w\" style=\"height:auto !important;\" /></p>");

        if (null != list) {
            for (MacVodDo macVodDo : list) {
                builder
                    .append("<section class=\"_135editor\" style=\"border: 0px none; box-sizing: border-box;\"><section class=\"layout\" style=\"margin: 10px auto; padding: 15px 5px; width: 60%; border-top: 1px solid rgb(191,191, 191); border-left-width: 0px; border-bottom: 1px solid rgb(191, 191, 191); line-height: 25px; text-align:center; border-right-color: rgb(191, 191, 191); border-left-color: rgb(191, 191, 191); font-size: 18px; color:rgb(242, 100, 124); box-sizing: border-box;\"><p>电影名称:<span style=\"color: #7030a0;\">");
                builder.append(macVodDo.getVodName()).append("</span></p></section></section>");
                String picUrl = macVodDo.getVodPic();
                if (StringUtils.contains(picUrl, "pic.php?pic=")) {
                    picUrl = StringUtils.replace(picUrl, "pic.php?pic=", "http://");
                }
                //图片地址
                builder
                    .append("<p style=\"text-align: center; margin-top: 10px;\"><img src=\"")
                    .append(picUrl)
                    .append(
                        "\" style=\"height:auto !important;\" /> </p> <p style=\"text-align: justify; margin-top: 10px;font-size: 14px; line-height: 1.8em; color: rgb(161, 161, 161);\"><br /></p>");

                //演员列表
                builder
                    .append(
                        "<section class=\"_135editor\" data-id=\"image-3805\" style=\"border: 0px none; box-sizing: border-box;\"><p style=\"text-align:center\"><img src=\"https://image2.135editor.com/cache/remote/aHR0cHM6Ly9tbWJpei5xbG9nby5jbi9tbWJpel9wbmcvemlhZEREUXhiQ0pFTkdjZ21DQnRwWUtyM0xkbTZndGliaWNSTlNyc0VvVlFHVXFjTUJ3NG9pYlRLYXdTOFBpY2JyNlcwbklUaHFaMThZR20yNEpuaWJpYjdPS3RBLzA/d3hfZm10PXBuZw==\" title=\"小清新图标\" data-model=\"Image\" style=\"max-width: 100%; float: left;\" alt=\"\" /><strong>演员:</strong><span style=\"color: #548dd4;\">")
                    .append(macVodDo.getVodActor().trim()).append("</span></p></section>");
                //区域
                builder
                    .append(
                        "    <p style=\"text-align: left;\"><img src=\"http://mpt.135editor.com/mmbiz_png/fgnkxfGnnkRpyWFC9dLM904aoouVrsNSsoMbhtDM078zTg3YAHl6icCbRFoDQRPAc5rBjCTRR1YJ1KxfmZNEaOw/0?wx_fmt=png\" title=\"船\" data-model=\"Image\" alt=\"\" /><strong>区域</strong>:<span style=\"color: #548DD4;\">")
                    .append(macVodDo.getVodArea()).append("</span></p>");

                //年份
                builder
                    .append(
                        "    <section class=\"_135editor\" data-id=\"image-32239\" style=\"border: 0px none; box-sizing: border-box;\"><p style=\"text-align: left;\"><img src=\"https://image2.135editor.com/cache/remote/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9mZ25reGZHbm5rVFdyNEVXeDkzMWFwV2wwQU1oS2pPbXlncXlSRVp4S3dQaWFaazd3ZW9CVklEQXNpYU8xa1NiSUVaT3VuelhpY2RIc2I0ckpqakdJMHd2Zy8wP3d4X2ZtdD1wbmc=\" title=\"冰淇凌\" data-model=\"Image\" style=\"max-width: 100%; width: 51px; height: 51px;\" alt=\"\" width=\"51\" height=\"51\" /><span style=\"caret-color: red;\"><strong>年份:</strong></span><span style=\"caret-color: red; color:#548dd4;\">")
                    .append(macVodDo.getVodYear()).append("</span></p></section>");

                //详细介绍
                builder
                    .append(
                        "    <p style=\"text-align: justify; margin-top: 10px;font-size: 14px; line-height: 1.8em; color: rgb(161, 161, 161);\"><span style=\"color: #000000;\"><strong>详细介绍</strong></span>:</p><p style=\"text-align: justify; margin-top: 10px; font-size: 14px; line-height: 1.8em; color: rgb(161, 161, 161);text-indent: 2em;\"><span style=\"color: #002060;\">")
                    .append(macVodDo.getVodContent()).append("</span></p><p><br /></p>");
            }

            //结尾
            builder
                .append("<p><img data-id=\"107\" data-role=\"guide-img\" title=\"猫头引导分享\" class=\"lazy\" src=\"http://image2.135editor.com/mmbiz/cZV2hRpuAPjOjIEA1OjSicXHcia9Mj9RQjXc01N4CGdhLm59FIUSibLQjhlhkcuzuurHmhHdqb66kGTxlUCYzv84w/0\" /></p><p style=\"text-align: justify; margin-top: 10px;font-size: 14px; line-height: 1.8em; color: rgb(161, 161, 161);\">只需关注公众号:购物猫，或者直接用浏览器打开本网址 </p><p style=\"text-align: justify; margin-top: 10px;font-size: 14px; line-height: 1.8em; color: rgb(161, 161,161);;text-align:center;\"><a href=\"http://vip.gouwumao.top/\">http://vip.gouwumao.top/ </a> </p> <p style=\"text-align: justify; margin-top:10px;font-size: 14px; line-height: 1.8em; color: rgb(161, 161, 161);;text-align:center;\">输入 电影+名称发给我们，即可免费观看呦！</p><p><img data-id=\"87549\" data-role=\"guide-img\" title=\"可爱小女孩动图爱心引导分享与赞\" class=\"lazy\" src=\"http://image2.135editor.com/mmbiz_gif/fgnkxfGnnkRfeUEET3ibCz0eadPptUdZ6RzabNhs8loeztFcROic38ibZjs5Km38sNfbPmdYCgzoRHrmwvmUpDOPw/0?wx_fmt=gif\" /></p><p><br /></p></section>");

            EmailOrder emailOrder = new EmailOrder();
            emailOrder.setContent(builder.toString());
            emailOrder.setTitle("订阅号文章模板:" + DateUtilSelf.sdtShortFormat(new Date()));
            emailOrder.setReceives(BaseConstants.EMAIL_RECEIVES);
            AliyunEmail.send(emailOrder);
            builder = null;
        }
    }

}
