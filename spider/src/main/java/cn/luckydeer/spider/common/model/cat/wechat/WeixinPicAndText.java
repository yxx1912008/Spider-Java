package cn.luckydeer.spider.common.model.cat.wechat;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyAttribute;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信图文消息
 * 
 * @author yuanxx
 * @version $Id: WeixinPicAndText.java, v 0.1 2018年9月28日 上午11:13:23 yuanxx Exp $
 */
@XStreamAlias(value = "xml")
public class WeixinPicAndText extends BaseWxMessage implements Serializable {

    /**  */
    private static final long       serialVersionUID = 3932935515380621726L;

    //消息类型 图文消息 默认news 
    private String                  MsgType;

    // 图文消息个数
    private Integer                 ArticleCount;

    //多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应
    @XmlAnyAttribute
    private List<WeixinPicTextItem> Articles;

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public Integer getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(Integer articleCount) {
        ArticleCount = articleCount;
    }

    public List<WeixinPicTextItem> getArticles() {
        return Articles;
    }

    public void setArticles(List<WeixinPicTextItem> articles) {
        Articles = articles;
    }

}
