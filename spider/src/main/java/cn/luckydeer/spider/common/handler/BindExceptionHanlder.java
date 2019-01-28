package cn.luckydeer.spider.common.handler;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import cn.luckydeer.spider.common.view.response.ResponseObj;
import cn.luckydeer.spider.common.view.show.ViewShowEnums;

/**
 * 
 * 自己定义的异常捕捉类
 * @author yuanxx
 * @version $Id: BindExceptionHanlder.java, v 0.1 2018年11月15日 下午4:36:19 yuanxx Exp $
 */
@RestControllerAdvice
public class BindExceptionHanlder {

    private final static Logger logger = LoggerFactory.getLogger("SYS-LOG");

    /**
     * 
     * 注解：捕获表单效验异常
     * 随机捕获一个异常并返回前台
     * @param ex
     * @return
     * @author yuanxx @date 2018年11月15日
     */
    @ExceptionHandler(BindException.class)
    public ResponseObj handleBindException(BindException ex) {
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        List<ObjectError> fieldError = ex.getAllErrors();
        String errorInfo = fieldError.get(0).getDefaultMessage();
        /**  被注释的参数 太过于详细 */
        // FieldError fieldError = ex.getFieldError();
        //StringBuilder sb = new StringBuilder();
        /*  sb.append(fieldError.getField()).append("=[").append(fieldError.getRejectedValue())
              .append("]").append(fieldError.getDefaultMessage());*/
        // 生成返回结果
        return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), errorInfo);
    }

    /**
     * 
     * 注解：捕获接口地址未找到异常
     * @param ex
     * @return
     * @author yuanxx @date 2018年12月5日
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseObj handleNoLoginException(NoHandlerFoundException ex) {
        logger.info("请求接口未找到，请求路径：{},请求方式：{}", ex.getRequestURL(), ex.getHttpMethod());
        System.out.println(ToStringBuilder.reflectionToString(ex));
        return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "接口未找到");
    }

    /**
     * 
     * 注解：捕获全局异常
     * @param ex
     * @return
     * @author yuanxx @date 2018年11月15日
     */
    @ExceptionHandler(Exception.class)
    public ResponseObj handleException(Exception ex) {
        logger.error("系统异常", ex);
        return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "系统异常");
    }

}
