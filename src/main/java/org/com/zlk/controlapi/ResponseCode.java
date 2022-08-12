package org.com.zlk.controlapi;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/8/12 09:35
 */
public enum ResponseCode {

    FAIL("-1", "网络异常，请稍后再试"),

    SUCCESS("0", "请求成功"),

    MAX_UPLOAD_SIZE_ERROR("1000", "上传文件不能超过20M"),

    SERVICE_BUSY_ERROR("1000", "服务器正在繁忙，请稍后再试哦~"),

    REQUEST_PARAMS_FAIL("1001", "参数错误"),

    USER_NOT_LOGIN("1002", "用户未登录，请重新登录"),

    USER_HAS_EXIST_LOGIN("1007", "用户已经存在，请检查！"),

    USER_CODE_NOT_EXIST("1008", "用户编码不存在，请检查！"),

    REQUEST_PARAMS_FORMAT_ERROR("1102", "请求参数格式异常"),

    PASSWORD_SAFETY_ERROE("2204", "密码不符合安全规则，请通过忘记密码重新设置8-18位数字+字母组合密码"),

    TOKEN_EXPIRED("2301", "token过期"),

    TOKEN_ERROR("2302", "token验证失败"),

    INTERFACE_ERROR("10000", "接口服务器异常");

    private final String code;
    private final String msg;

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getDefaultMsg(){
        return "defaultMsg=====";
    }
}
