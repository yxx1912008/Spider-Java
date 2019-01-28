package cn.luckydeer.spider.dao.movie.dataObject;

import java.math.BigDecimal;

/**
 * 
 * 苹果影音 电影信息映射
 * @author yuanxx
 * @version $Id: MacVodDo.java, v 0.1 2018年10月12日 上午11:29:22 yuanxx Exp $
 */
public class MacVodDo {

    private Integer    vodId;     //电影ID

    private String     vodName;   //名称

    private String     vodClass;  //电影类目

    private String     vodPic;    //电影图片地址

    private BigDecimal vodScore;  //电影评分

    private Integer    vodTimeAdd; //影视添加时间

    private String     vodContent; //电影内容

    private String     vodActor;  //演员表

    private String     vodArea;   //电影区域

    private String     vodYear;    //电影年份

    public Integer getVodId() {
        return vodId;
    }

    public void setVodId(Integer vodId) {
        this.vodId = vodId;
    }

    public String getVodName() {
        return vodName;
    }

    public void setVodName(String vodName) {
        this.vodName = vodName;
    }

    public String getVodClass() {
        return vodClass;
    }

    public void setVodClass(String vodClass) {
        this.vodClass = vodClass;
    }

    public String getVodPic() {
        return vodPic;
    }

    public void setVodPic(String vodPic) {
        this.vodPic = vodPic;
    }

    public BigDecimal getVodScore() {
        return vodScore;
    }

    public void setVodScore(BigDecimal vodScore) {
        this.vodScore = vodScore;
    }

    public Integer getVodTimeAdd() {
        return vodTimeAdd;
    }

    public void setVodTimeAdd(Integer vodTimeAdd) {
        this.vodTimeAdd = vodTimeAdd;
    }

    public String getVodContent() {
        return vodContent;
    }

    public void setVodContent(String vodContent) {
        this.vodContent = vodContent;
    }

    public String getVodActor() {
        return vodActor;
    }

    public void setVodActor(String vodActor) {
        this.vodActor = vodActor;
    }

    public String getVodArea() {
        return vodArea;
    }

    public void setVodArea(String vodArea) {
        this.vodArea = vodArea;
    }

    public String getVodYear() {
        return vodYear;
    }

    public void setVodYear(String vodYear) {
        this.vodYear = vodYear;
    }

}