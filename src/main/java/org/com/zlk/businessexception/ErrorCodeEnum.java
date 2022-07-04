package org.com.zlk.businessexception;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 异常情况枚举
 * @Date 2022/7/2 10:02
 */
public enum ErrorCodeEnum implements BaseEnum{

    FAIL(-1, "网络异常，请稍后再试"),

    SUCCESS(0, "请求成功"),

    MAX_UPLOAD_SIZE_ERROR(1000, "上传文件不能超过20M"),

    SERVICE_BUSY_ERROR(1000, "服务器正在繁忙，请稍后再试哦~"),

    REQUEST_PARAMS_FAIL(1001, "参数错误"),

    USER_NOT_LOGIN(1002, "用户未登录，请重新登录"),

    USER_HAS_EXIST_LOGIN(1007, "用户已经存在，请检查！"),

    USER_CODE_NOT_EXIST(1008, "用户编码不存在，请检查！"),

    REQUEST_PARAMS_FORMAT_ERROR(1102, "请求参数格式异常"),

    PASSWORD_SAFETY_ERROE(2204, "密码不符合安全规则，请通过忘记密码重新设置8-18位数字+字母组合密码"),

    TOKEN_EXPIRED(2301, "token过期"),

    TOKEN_ERROR(2302, "token验证失败"),

    INTERFACE_ERROR(10000, "接口服务器异常");

    private final int code;
    private final String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
