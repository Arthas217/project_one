package org.com.zlk.businessexception.v3;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/19 19:27
 */
public class WebException extends BusinessException{
    private static final long serialVersionUID = -2859788564047558354L;


    public WebException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
