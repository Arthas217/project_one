package org.com.zlk.businessexception.v3;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/19 18:55
 */
public class ResponseBean {

    public static ResponseBean error(int value,String message) {
        ResponseBean responseBean = new ResponseBean();
        return responseBean;
    }
}
