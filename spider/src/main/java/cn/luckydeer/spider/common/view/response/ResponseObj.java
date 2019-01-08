package cn.luckydeer.spider.common.view.response;

import java.io.Serializable;

import cn.luckydeer.spider.common.view.show.ViewShowEnums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 功能：web与app通用的选择json
 * 
 * @author yuanxx
 * @version $Id: ResponseObj.java, v 0.1 2018年11月15日 下午2:58:23 yuanxx Exp $
 */
@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseObj implements Serializable {

    private static final long serialVersionUID = -7049443021893341045L;

    private int               status;
    /**
     * 前台显示的提示信息
     */
    private String            showMessage;
    /**
     * 返回app端的的json数据字符串
     */
    private Object            data;

    // 异常情况
    public ResponseObj(int status, String showMessage) {
        this.status = status;
        this.showMessage = showMessage;
    }

    // 正确情况
    public ResponseObj() {
        this.status = ViewShowEnums.INFO_SUCCESS.getStatus();
        this.showMessage = ViewShowEnums.INFO_SUCCESS.getDetail();
    }

    // 正确情况
    public ResponseObj(Object data) {
        this.status = ViewShowEnums.INFO_SUCCESS.getStatus();
        this.showMessage = ViewShowEnums.INFO_SUCCESS.getDetail();
        this.data = data;
    }

    // 正确情况
    public ResponseObj(int status, String showMessage, Object data) {
        this.status = status;
        this.showMessage = showMessage;
        this.data = data;
    }

    /** <p class="detail">
    * 功能：0:失败; 1:成功
    * </p>
    * @author panwuhai
    * @date 2016年4月17日 
    * @return    
    */
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    public String getShowMessage() {
        return showMessage;
    }

}
