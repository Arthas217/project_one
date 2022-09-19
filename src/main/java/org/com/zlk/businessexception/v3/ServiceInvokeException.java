package org.com.zlk.businessexception.v3;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/19 19:09
 */
public class ServiceInvokeException extends BusinessException{
    private static final long serialVersionUID = 1202733133384581484L;

    public ServiceInvokeException(String message) {
        super(message);
    }
}
