package cn.luckydeer.spider.common.model.news;

import java.io.Serializable;

public class FengNews implements Serializable {

    /**  */
    private static final long serialVersionUID = -4865406020006090417L;

    /**  新闻详情地址 */
    private String            url;

    /**  喜欢本文章的人数 预留字段 */
    private int               like_count;

    /** 标题 */
    private String            title;

    /** 发布时间：格式化后 */
    private String            post_date;

    /** 发布时间戳 */
    private String            post_time;

    /** 陈知心 */
    private String            from;

    /** 缩略图地址 */
    private String            pic_url;

    /** 文章内容 */
    private String            content;

    /** 多少人看过 */
    private String            view_count;

    /** 评论人数 */
    private String            comment_count;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getView_count() {
        return view_count;
    }

    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

}
