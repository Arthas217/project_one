package org.com.zlk.businessexception.v3;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/19 19:29
 */
public class TokenErrorException extends BusinessException{
    private static final long serialVersionUID = 8693883741422071924L;

    public TokenErrorException(String message) {
        super(message);
    }
}
