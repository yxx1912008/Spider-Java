package cn.luckydeer.spider.common.model.cat.banner;

import java.io.Serializable;

public class BannerModel implements Serializable {

    /**  */
    private static final long serialVersionUID = -5139607738820224013L;

    //商品ID
    private String            goodId;

    //图片地址
    private String            bannerImg;

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

}
