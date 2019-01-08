package cn.luckydeer.spider.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 当前环境类型
 * @author yuanxx
 * @version $Id: VmType.java, v 0.1 2018年11月15日 上午9:28:38 yuanxx Exp $
 */
public enum VmType {

    DEBUG("DEBUG", "测试环境"), PRO("PRO", "生产环境");

    private String status;

    private String detail;

    VmType(String status, String detail) {
        this.setStatus(status);
        this.setDetail(detail);
    }

    /**
     * 获得枚举
     * 
     * @param code
     * @return
     */
    public static VmType getEnumByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (VmType type : VmType.values()) {
                if (StringUtils.equals(code, type.getStatus())) {
                    return type;
                }
            }
        }
        return null;
    }

    /**
     * 
     * 注解：通过状态值获取详情
     * @param code
     * @return
     * @author yuanxx @date 2018年11月15日
     */
    public static String getDetailByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (VmType type : VmType.values()) {
                if (StringUtils.equals(code, type.getStatus())) {
                    return type.getDetail();
                }
            }
        }
        return null;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
