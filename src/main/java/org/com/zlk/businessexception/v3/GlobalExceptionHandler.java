package org.com.zlk.businessexception.v3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
