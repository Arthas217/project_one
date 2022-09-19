package org.com.zlk.businessexception.v3;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/19 19:32
 */
public class ServiceException extends BusinessException{
    private static final long serialVersionUID = -7843036921295752834L;

    public ServiceException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
