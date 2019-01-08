/**
 * icaomei Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package cn.luckydeer.spider.common.view.response;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * 返回给客户端的json串
 * @author panwuhai
 * @version $Id: UrlResponse.java, v 0.1 2018年8月13日 下午3:47:56 panwuhai Exp $
 */
public class UrlResponse {

    public static String toJson(ResponseObj responseObj, HttpServletRequest request,
                                HttpServletResponse response) {

        // 设置跨域
        DomainUtils.setAccessContrlAllowOrigin(request, response);

        String resultJson = null;

        if (null == responseObj.getData()
            || (responseObj.getData() instanceof String && StringUtils.isBlank(responseObj
                .getData().toString()))
            || (responseObj.getData() instanceof Collection && CollectionUtils
                .isEmpty((Collection<?>) responseObj.getData()))) {
            resultJson = getBlankDataJson(responseObj);
            return resultJson;
        }

        return GsonUtils.getGson().toJson(responseObj);
    }

    /** <p class="detail">
     * 功能：data数据为空时配合app的json结果
     * </p>
     * @author panwuhai
     * @date 2016年4月12日 
     * @return    
     */
    private static String getBlankDataJson(ResponseObj responseObj) {
        StringBuilder builder = new StringBuilder("{\"status\":");
        builder.append(responseObj.getStatus());
        builder.append(",\"showMessage\":\"");
        builder.append(responseObj.getShowMessage());
        builder.append("\",\"data\":{}}");
        return builder.toString();
    }

}
