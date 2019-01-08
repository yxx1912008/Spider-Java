package cn.luckydeer.spider.common.view.show;

/**
 * 非业务类型枚举
 * 不需要显示下拉菜单
 * 
 * @author yuanxx
 * @version $Id: ViewShowEnums.java, v 0.1 2018年11月15日 下午2:59:23 yuanxx Exp $
 */
public enum ViewShowEnums {

    NOT_LOGIN(-1, "重新登录"), ERROR_FAILED(0, "系统异常"), INFO_SUCCESS(1, "操作成功");

    private int    status;

    private String detail;

    /**
     * 返回结果是否错误
     * @return
     */
    public boolean isError() {
        switch (this) {
            case INFO_SUCCESS:
                return false;
            default:
                return true;
        }
    }

    public boolean isSuccess() {
        switch (this) {
            case INFO_SUCCESS:
                return true;
            default:
                return false;
        }
    }

    ViewShowEnums(int status, String detail) {
        this.status = status;
        this.detail = detail;
    }

    /**
     * 获得枚举
     * 
     * @param code
     * @return
     */
    public static ViewShowEnums getEnumByStatus(Integer status) {
        if (null != status) {
            for (ViewShowEnums activitie : ViewShowEnums.values()) {
                if (status.intValue() == activitie.getStatus()) {
                    return activitie;
                }
            }
        }
        return null;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     *            the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

}
