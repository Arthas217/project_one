package org.com.zlk.businessexception.v3;

import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 增加异常处理类
 * @Date 2022/7/4 12:28
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseBean businessExceptionHandler(BusinessException e) {
        log.info("business error : {}", e);
        if (e.getCode() == -1) {
            return ResponseBean.error(ApiCode.SERVICE_ERROR.getValue(), ApiCode.SERVICE_ERROR.getMessage());
        }
        return ResponseBean.error(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(value = com.alibaba.fastjson.JSONException.class)
    public ResponseBean alibabaJsonExceptionHandler(com.alibaba.fastjson.JSONException e) {
        ResponseBean response = new ResponseBean(false, ApiCode.PARAM_FORMAT_INCORR.getValue(), ApiCode.PARAM_FORMAT_INCORR.getMessage() + e.getMessage(), null);
        log.error("1102", e);
        return response;
    }


    @ExceptionHandler(value = JsonParseException.class)
    @ResponseBody
    public ResponseBean jsonParseExceptionHandler(JsonParseException e) {
        ResponseBean response = new ResponseBean(false, ApiCode.PARAM_FORMAT_INCORR.getValue(), String.format(ApiCode.PARAM_FORMAT_INCORR.getMessage() + ":%s", e.getMessage()), null);
        log.error(ApiCode.PARAM_FORMAT_INCORR.getValue() + "", e);
        return response;
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseBean exceptionHandler(Exception e) {
        ResponseBean response = new ResponseBean(false, ApiCode.SERVICE_ERROR.getValue(), ApiCode.SERVICE_ERROR.getMessage(), null);
        log.error(ApiCode.SERVICE_ERROR.getValue() + "", e);
        return response;
    }



    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseBean exceptionHandle(MethodArgumentTypeMismatchException e) {
        ResponseBean response = new ResponseBean(false, ApiCode.PARAM_FORMAT_INCORR.getValue(), String.format(ApiCode.PARAM_FORMAT_INCORR.getMessage() + ":%s", e.getMessage()), null);
        log.error(ApiCode.PARAM_FORMAT_INCORR.getValue() + "", e);
        return response;
    }


    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public ResponseBean exceptionHandler(IllegalArgumentException e) {
        log.error("illegal request : {}", e.getMessage(), e);
        return ResponseBean.error(ApiCode.PARAM_INVALID.getValue(), ApiCode.PARAM_INVALID.getMessage());
    }


    @ExceptionHandler(value = ServiceInvokeException.class)
    @ResponseBody
    public ResponseBean exceptionHandler(ServiceInvokeException e) {
        log.error("serviceInvoke error request : {}", e.getMessage(), e);
        return ResponseBean.error(ApiCode.SERVICE_ERROR.getValue(), ApiCode.SERVICE_ERROR.getMessage());
    }



    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBean  exceptionHandler(MethodArgumentNotValidException e) {
        log.info("req params error", e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        if (StringUtils.isNotBlank(message) && !"不能为空".equals(message)) {
            return ResponseBean.error(ApiCode.PARAM_INVALID.getValue(), message);
        }
        return ResponseBean.error(ApiCode.PARAM_INVALID.getValue(), ApiCode.PARAM_INVALID.getMessage());
    }


    @ExceptionHandler(value = TokenErrorException.class)
    @ResponseBody
    public ResponseBean tokenErrorExceptionHandler(TokenErrorException e) {
        log.info("登录失效 : {}",e.getMessage(),e);
        return ResponseBean.error(ApiCode.SERVICE_ERROR.getValue(), "登录已失效,请重新登录！");
    }

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public ResponseBean businessExceptionHandler(ServiceException e) {
        log.info("service error : {}",e.getMessage(),e);
        return ResponseBean.error(ApiCode.SERVICE_ERROR.getValue(), e.getMessage());
    }

//    @ExceptionHandler(value = WebException.class)
//    @ResponseBody
//    public ResponseBean exceptionHandler(WebException e) {
//        ResponseBean response = new ResponseBean(e.getIsSuccess(), e.getResponseCode(), e.getResponseMsg(), null);
//        log.error(e.getResponseCode() + "", e);
//        return response;
//    }

}
