package cn.luckydeer.spider.common.model.cat.wechat;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * 
 * @author yuanxx
 * @version $Id: item.java, v 0.1 2018年9月28日 上午11:40:47 yuanxx Exp $
 */
@XStreamAlias("item")
public class WeixinPicTextItem implements Serializable {

    /**  */
    private static final long serialVersionUID = 3418165069211525240L;

    //标题
    private String            Title;

    //图文消息描述
    private String            Description;

    //图片
    private String            PicUrl;
    //跳转地址
    private String            Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

}
