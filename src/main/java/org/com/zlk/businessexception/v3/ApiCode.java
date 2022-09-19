package org.com.zlk.businessexception.v3;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/19 18:51
 */
public enum ApiCode {

    SERVICE_ERROR(503, "服务器错误"),
    PARAM_FORMAT_INCORR(100, "参数格式不正确"),
    PARAM_INVALID(101,"参数不合法");

    private int value;
    private String message;

    ApiCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }
    public String getMessage() {
        return message;
    }
}
