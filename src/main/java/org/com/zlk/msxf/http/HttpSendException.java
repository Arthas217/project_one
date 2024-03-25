package org.com.zlk.msxf.http;

/**
 * @author 会游泳的蚂蚁
 * @date 2024/3/23 14:57
 */
public class HttpSendException extends RuntimeException{

    private Integer httpStatus;

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public HttpSendException(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpSendException(String message, Integer httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpSendException(String message, Throwable cause, Integer httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }
}
