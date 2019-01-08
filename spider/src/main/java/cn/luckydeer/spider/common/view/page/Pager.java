package cn.luckydeer.spider.common.view.page;

import java.io.Serializable;

/**
 * 分页模型
 * 
 * @author yuanxx
 * @version $Id: Pager.java, v 0.1 2018年11月15日 上午11:20:15 yuanxx Exp $
 */
public class Pager implements Serializable {

    /**
     * <p class="detail">
     * 功能：serialVersionUID
     * </p>
     */
    private static final long serialVersionUID = 2558832243642279010L;

    /** 
     * 列表会话查询时间段，防止刷新翻页数据重复，由客户端决定传递
     * 首次为空后台传递，第二页开始由客户端带回来
     * yyyy-MM-dd HH:mm:ss
     *  */
    protected String          scanTime;

    /** 当前第几页  app必传项 */
    protected Integer         currentPage;

    // 页码， app可选项
    protected Integer         pageSize;

    /** mysql 每页起始行 */
    protected Integer         startRow;

    public Pager() {
        super();
    }

    public Pager(Integer currentPage, Integer pageSize) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public String getScanTime() {
        return scanTime;
    }

    public void setScanTime(String scanTime) {
        this.scanTime = scanTime;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return startRow
     */
    public Integer getStartRow() {
        return startRow;
    }

    /**
     * @param startRow
     */
    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    /**
     * @return pageSize
     */

    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
