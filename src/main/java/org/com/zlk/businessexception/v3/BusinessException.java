package org.com.zlk.businessexception.v3;

import org.apache.log4j.Level;

/**
 * @Author 会游泳的蚂蚁
 * @Description:  增加异常类
 * 业务异常，异常信息会返回到前端展示给用户
 * @Date 2022/7/2 14:38
 */
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = -2301063433390281252L;
    private int code = 1;
    private Level level;

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Level level, String message) {
        super(message);
        this.level = level;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public final Level getLevel() {
        return this.level;
    }
}
