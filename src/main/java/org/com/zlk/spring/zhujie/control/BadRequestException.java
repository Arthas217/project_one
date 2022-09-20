package org.com.zlk.spring.zhujie.control;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/20 09:16
 */
public class BadRequestException extends TestException{
    private static final long serialVersionUID = 1632065227396322268L;

    public BadRequestException(String errorMsg) {
        super(errorMsg);
    }

    public BadRequestException(Integer innerCode, String errorMsg) {
        super(innerCode, errorMsg);
    }
}
