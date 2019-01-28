package cn.luckydeer.spider.common.model.cat.goods;

import java.io.Serializable;
import java.util.Date;

/**
 * 购物猫搜索到的商品详情
 * 
 * @author yuanxx
 * @version $Id: SearchGoodInfo.java, v 0.1 2018年9月28日 下午1:53:26 yuanxx Exp $
 */
public class SearchGoodInfo  implements Serializable{

    /**  */
    private static final long serialVersionUID = -4075197544525900162L;
    //原价
    private double yuanjia;
    //商品ID
    private String goodsid;
    //商品标题
    private String d_title;
    //商品图片地址
    private String pic;
    //优惠券连接
    private int    quan_jine;
    //人气
    private int    renqi;
    //更新时间
    private Date   updatetime;
    //
    private long   id;
    //价格
    private double jiage;
    //是否是天猫
    private int    istmall;

    private String from;

    private int    xiaoliang;

    public double getYuanjia() {
        return yuanjia;
    }

    public void setYuanjia(double yuanjia) {
        this.yuanjia = yuanjia;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getD_title() {
        return d_title;
    }

    public void setD_title(String d_title) {
        this.d_title = d_title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getQuan_jine() {
        return quan_jine;
    }

    public void setQuan_jine(int quan_jine) {
        this.quan_jine = quan_jine;
    }

    public int getRenqi() {
        return renqi;
    }

    public void setRenqi(int renqi) {
        this.renqi = renqi;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getJiage() {
        return jiage;
    }

    public void setJiage(double jiage) {
        this.jiage = jiage;
    }

    public int getIstmall() {
        return istmall;
    }

    public void setIstmall(int istmall) {
        this.istmall = istmall;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getXiaoliang() {
        return xiaoliang;
    }

    public void setXiaoliang(int xiaoliang) {
        this.xiaoliang = xiaoliang;
    }

}
