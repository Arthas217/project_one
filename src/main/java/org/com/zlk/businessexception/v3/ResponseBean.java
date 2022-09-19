package org.com.zlk.businessexception.v3;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/19 18:55
 */
public class ResponseBean<T> {

    private boolean flag;
    private int value;
    private String message;
    private T data;

    public ResponseBean(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public ResponseBean(boolean flag, int value, String message,T data) {
        this.flag = flag;
        this.value = value;
        this.message = message;
        this.data =data;
    }

    public static ResponseBean error(int value, String message) {
        ResponseBean responseBean = new ResponseBean(value,message);
        return responseBean;
    }

}
