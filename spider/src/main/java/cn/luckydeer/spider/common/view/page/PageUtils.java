package cn.luckydeer.spider.common.view.page;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import cn.luckydeer.spider.common.utils.date.DateUtilSelf;

/**
 * 
 * 分页计算工具类
 * 
 * @author yuanxx
 * @version $Id: PageUtils.java, v 0.1 2018年11月15日 下午3:04:15 yuanxx Exp $
 */
public class PageUtils {

    /**
     * <p class="detail">
     * 功能：根据页码 与每页行数 计算 分页需要的sql参数
     * </p>
     * @param pager
     * @author yuanxx @date 2018年11月15日
     */
    public static void computePage(Pager pager) {

        // spring_mvc pager入参 总是不为null
        if (null == pager) {
            pager = new Pager();
        }
        // 页码
        Integer pageSize = pager.getPageSize();
        // 页码
        if (null == pageSize || pageSize.intValue() < PageContants.MIN_PAGE_SIZE
            || pageSize.intValue() > PageContants.MAX_PAGE_SIZE) {
            pageSize = PageContants.DEFULT_PAGE_SIZE;
            pager.setPageSize(pageSize);
        }
        // 第几页
        Integer currentPage = pager.getCurrentPage();

        if (null == currentPage || currentPage.intValue() < 1) {
            currentPage = 1;
            pager.setCurrentPage(currentPage);
        }
        // 前端未传递当前会话时间段则每次查最新的时间段
        if (StringUtils.isBlank(pager.getScanTime())) {
            pager.setScanTime(DateUtilSelf.simpleFormat(new Date()));
        }
        pager.setStartRow(pageSize * (currentPage - 1));
    }

    public static void computePageByExcel(Pager pager) {
        // 页码
        Integer pageSize = pager.getPageSize();

        // 页码
        /**  excel 一页最大65535行 这里设置6万行一页另起一个sheet */
        if (null == pageSize || pageSize.intValue() < 1 || pageSize.intValue() > 60000) {
            pageSize = 60000;
            pager.setPageSize(pageSize);
        }

        // 第几页
        Integer currentPage = pager.getCurrentPage();

        if (null == currentPage || currentPage.intValue() < 1) {
            currentPage = 1;
            pager.setCurrentPage(currentPage);
        }

        pager.setStartRow(pageSize * (currentPage - 1));

    }

}
