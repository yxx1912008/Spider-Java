package cn.luckydeer.spider.dao.home.daoObject;

/**
 * 系统默认配置
 * 
 * @author yuanxx
 * @version $Id: SysOptionsDo.java, v 0.1 2019年1月26日 上午10:40:30 yuanxx Exp $
 */
public class SysOptionsDo {
    
    /**   */
    private Integer optionId;

    /**   */
    private String  optionName;

    /**   */
    private String  optionValue;

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }
}