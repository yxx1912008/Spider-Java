package cn.luckydeer.spider.common.utils.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 取整时间工具类
 * @author yuanxx
 * @version $Id: RoundingDateUtil.java, v 0.1 2018年11月15日 下午3:23:43 yuanxx Exp $
 */
public class RoundingDateUtil {

    /**
     * @param format "yyyyMMdd HH:mm:ss.SSS"
     * @return
     */
    private static final DateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }

    /**
     * @param date
     * 
     * @return
     */
    public static String formatDate(Date date, String format) {
        return getFormat(format).format(date);
    }

    /**
     * 月初
     * @param date
     * @param before  几月前
     * @return
     */
    public static Date getStartTimeByBeforeMonth(Date date, int before) {
        Calendar cad = Calendar.getInstance();
        cad.setTime(date);
        cad.add(Calendar.MONTH, -before);
        // 取完上月 再取上月初
        cad.set(Calendar.DAY_OF_MONTH, cad.getActualMinimum(Calendar.DAY_OF_MONTH));
        cad.set(Calendar.HOUR_OF_DAY, 0);
        cad.set(Calendar.MINUTE, 0);
        cad.set(Calendar.SECOND, 0);
        cad.set(Calendar.MILLISECOND, 0);
        return cad.getTime();
    }

    /**
     * 月末
     * @param date
     * @param before  几月前
     * @return
     */
    public static Date getEndTimeByBeforeMonth(Date date, int before) {
        Calendar cad = Calendar.getInstance();
        cad.setTime(date);
        cad.add(Calendar.MONTH, -before);
        // 取完上月 再取上月初
        cad.set(Calendar.DAY_OF_MONTH, cad.getActualMaximum(Calendar.DAY_OF_MONTH));
        cad.set(Calendar.HOUR_OF_DAY, 23);
        cad.set(Calendar.MINUTE, 59);
        cad.set(Calendar.SECOND, 59);
        cad.set(Calendar.MILLISECOND, 999);
        return cad.getTime();
    }

    /**
     * 本月初 201408010000000
     * @param date
     * @return
     */
    public static Date getStartTimeByMonth(Date date) {
        Calendar cad = Calendar.getInstance();
        cad.setTime(date);
        cad.set(Calendar.DAY_OF_MONTH, cad.getActualMinimum(Calendar.DAY_OF_MONTH));
        cad.set(Calendar.HOUR_OF_DAY, 0);
        cad.set(Calendar.MINUTE, 0);
        cad.set(Calendar.SECOND, 0);
        cad.set(Calendar.MILLISECOND, 0);
        return cad.getTime();
    }

    /**
     * 本月月末20140931235959999
     * @param date
     * @return
     */
    public static Date getEndTimeByMonth(Date date) {
        Calendar cad = Calendar.getInstance();
        cad.setTime(date);
        cad.set(Calendar.DAY_OF_MONTH, cad.getActualMaximum(Calendar.DAY_OF_MONTH));
        cad.set(Calendar.HOUR_OF_DAY, 23);
        cad.set(Calendar.MINUTE, 59);
        cad.set(Calendar.SECOND, 59);
        cad.set(Calendar.MILLISECOND, 999);
        return cad.getTime();
    }

    /**
     * 本年初 201401010000000
     * @param date
     * @return
     */
    public static Date getStartTimeByYear(Date date) {
        Calendar cad = Calendar.getInstance();
        cad.setTime(date);
        cad.set(Calendar.MONTH, 1 - 1);
        cad.set(Calendar.DAY_OF_MONTH, cad.getActualMinimum(Calendar.DAY_OF_MONTH));
        cad.set(Calendar.HOUR_OF_DAY, 0);
        cad.set(Calendar.MINUTE, 0);
        cad.set(Calendar.SECOND, 0);
        cad.set(Calendar.MILLISECOND, 0);
        return cad.getTime();
    }

    /**
     * 本年末20141231235959999
     * @param date
     * @return
     */
    public static Date getEndTimeByYear(Date date) {
        Calendar cad = Calendar.getInstance();
        cad.setTime(date);
        cad.set(Calendar.MONTH, 12 - 1);
        cad.set(Calendar.DAY_OF_MONTH, cad.getActualMaximum(Calendar.DAY_OF_MONTH));
        cad.set(Calendar.HOUR_OF_DAY, 23);
        cad.set(Calendar.MINUTE, 59);
        cad.set(Calendar.SECOND, 59);
        cad.set(Calendar.MILLISECOND, 999);
        return cad.getTime();
    }

    /**
     * 本周初201408040000000
     * @param date
     * @return
     */
    public static Date getStartTimeByWeek(Date date) {
        Calendar cad = Calendar.getInstance();
        cad.setTime(date);
        // 设置周一为本周周初
        cad.setFirstDayOfWeek(Calendar.MONDAY);
        // 取本周周一
        cad.set(Calendar.DAY_OF_WEEK, cad.getFirstDayOfWeek());
        cad.set(Calendar.HOUR_OF_DAY, 0);
        cad.set(Calendar.MINUTE, 0);
        cad.set(Calendar.SECOND, 0);
        cad.set(Calendar.MILLISECOND, 0);
        return cad.getTime();
    }

    /**
     * 本周末 20140810235959999
     * @param date
     * @return
     */
    public static Date getEndTimeByWeek(Date date) {
        Calendar cad = Calendar.getInstance();
        cad.setTime(date);
        // 设置周一为本周周初
        cad.setFirstDayOfWeek(Calendar.MONDAY);
        // 取本周周日
        cad.set(Calendar.DAY_OF_WEEK, cad.getFirstDayOfWeek() + 6);
        cad.set(Calendar.HOUR_OF_DAY, 23);
        cad.set(Calendar.MINUTE, 59);
        cad.set(Calendar.SECOND, 59);
        cad.set(Calendar.MILLISECOND, 999);
        return cad.getTime();
    }

    /**
     * 前几天的起始时间
     * @param Date
     * @param before
     * @return
     */
    public static final Date getStartTimeByBeforeDay(Date Date, int before) {
        if (null != Date) {
            Calendar cad = Calendar.getInstance();
            cad.setTime(Date);
            cad.add(Calendar.DATE, -before);
            cad.set(Calendar.HOUR_OF_DAY, 0);
            cad.set(Calendar.MINUTE, 0);
            cad.set(Calendar.SECOND, 0);
            cad.set(Calendar.MILLISECOND, 0);
            return cad.getTime();
        }
        return null;
    }

    /**
     * 几天前的结束时间
     * @param Date
     * @return
     */
    public static final Date getEndTimeByBeforeDay(Date Date, int before) {
        if (null != Date) {
            Calendar cad = Calendar.getInstance();
            cad.setTime(Date);
            cad.add(Calendar.DATE, -before);
            cad.set(Calendar.HOUR_OF_DAY, 23);
            cad.set(Calendar.MINUTE, 59);
            cad.set(Calendar.SECOND, 59);
            cad.set(Calendar.MILLISECOND, 999);
            return cad.getTime();
        }
        return null;
    }

    /**
     * 当天初始时间
     * @param Date
     * @return
     */
    public static final Date getStartTimeByDay(Date Date) {
        if (null != Date) {
            Calendar cad = Calendar.getInstance();
            cad.setTime(Date);
            cad.set(Calendar.HOUR_OF_DAY, 0);
            cad.set(Calendar.MINUTE, 0);
            cad.set(Calendar.SECOND, 0);
            cad.set(Calendar.MILLISECOND, 0);
            return cad.getTime();
        }
        return null;
    }

    /**
     * 当天结束时间
     * @param Date
     * @return
     */
    public static final Date getEndTimeByDay(Date Date) {
        if (null != Date) {
            Calendar cad = Calendar.getInstance();
            cad.setTime(Date);
            cad.set(Calendar.HOUR_OF_DAY, 23);
            cad.set(Calendar.MINUTE, 59);
            cad.set(Calendar.SECOND, 59);
            cad.set(Calendar.MILLISECOND, 999);
            return cad.getTime();
        }
        return null;
    }

    /**
     * 当前小时初始
     * @param Date
     * @return
     */
    public static final Date getStartTimeByHour(Date Date) {
        if (null != Date) {
            Calendar cad = Calendar.getInstance();
            cad.setTime(Date);
            cad.set(Calendar.MINUTE, 0);
            cad.set(Calendar.SECOND, 0);
            cad.set(Calendar.MILLISECOND, 0);
            return cad.getTime();
        }
        return null;
    }

    /**
     * 当前小时结束时间
     * @param Date
     * @return
     */
    public static final Date getEndTimeByHour(Date Date) {
        if (null != Date) {
            Calendar cad = Calendar.getInstance();
            cad.setTime(Date);
            cad.set(Calendar.MINUTE, 59);
            cad.set(Calendar.SECOND, 59);
            cad.set(Calendar.MILLISECOND, 999);
            return cad.getTime();
        }
        return null;
    }

    /**
     * 定点定时
     * @param Date
     * @param hour 0~23
     * @return
     */
    public static final Date getHourTimeByDay(Date Date, int hour) {
        if (null != Date) {
            Calendar cad = Calendar.getInstance();
            cad.setTime(Date);
            cad.set(Calendar.HOUR_OF_DAY, hour);
            cad.set(Calendar.MINUTE, 0);
            cad.set(Calendar.SECOND, 0);
            cad.set(Calendar.MILLISECOND, 0);
            return cad.getTime();
        }
        return null;

    }

    /**
     * 注解：
     * @param date
     * @return
     * @author JT @date 2017年10月13日
     */
    public static final int getHoursByDay(Date date) {
        if (null != date) {
            Calendar cad = Calendar.getInstance();
            cad.setTime(date);
            return cad.get(Calendar.HOUR_OF_DAY);
        }
        return 0;
    }

}
