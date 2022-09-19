package org.com.zlk.businessexception.v3;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/19 19:27
 */
public class WebException extends RuntimeException {
    private static final long serialVersionUID = -2859788564047558354L;
    private boolean flag;
    private int responseCode;
    private String responseMsg;
    private Object data;

    public WebException(boolean flag, int code, String message, Object data) {
        this.flag = flag;
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
        this.data = data;
    }

    public boolean getIsSuccess() {
        return flag;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public Object getData() {
        return data;
    }
}
