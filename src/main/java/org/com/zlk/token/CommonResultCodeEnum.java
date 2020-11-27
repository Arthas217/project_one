package org.com.zlk.token;

/**
 * 接口返回状态代码的枚举类
 *
 * @Date 2020/11/26
 */
public enum  CommonResultCodeEnum {
    /**
     * 操作成功 {@code code=1}
     */
    SUCCESS(1,"操作成功"),

    /**
     * 操作失败，请稍候再试 {@code code=0}
     */
    FATAL(0,"操作失败，请稍候再试"),

    /**
     * 参数解析失败 {@code code=40000}
     */
    PARAMETER_ANALYSIS_FAILURE(40000,"参数解析失败"),

    /**
     * 接口授权认证失败，签名不匹配 {@code code=40001}
     */
    SIGNATURE_MISMATCH(40001,"接口授权认证失败，签名不匹配"),

    /**
     * 参数不足 {@code code=40002}
     */
    NULL_PARAMS_DATA(40002,"参数不足"),

    /**
     * 参数值为空 {@code code=40003}
     */
    NULL_PARAMS_VALUE(40003,"参数值为空"),

    /**
     * 缺少timestamp参数 {@code code=40004}
     */
    MISSING_TIMESTAMP(40004,"缺少timestamp参数"),

    /**
     * 缺少nonceStr参数 {@code code=40005}
     */
    MISSING_NONCESTR(40005,"缺少nonceStr参数"),

    /**
     * 缺少appkey参数 {@code code=40006}
     */
    MISSING_APPKEY(40006,"缺少appKey参数"),

    /**
     * 缺少secretkey参数 {@code code=40007}
     */
    MISSING_SECRETKEY(40007,"缺少secretKey参数"),

    /**
     * 服务器执行错误 {@code code=50000}
     */
    ERROR(50000,"服务器执行错误"),

    /**
     * 请求数据不存在，或数据集为空 {@code code=60000}
     */
    NULL_RESULT_DATA(60000,"请求数据不存在，或数据集为空");

    private int value;
    private String text;

    private CommonResultCodeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * 获取value
     *
     * @return int
     */
    public int getValue() {
        return value;
    }

    /**
     * 设置 value
     *
     * @param value int
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * 获取text
     *
     * @return String
     */
    public String getText() {
        return text;
    }

    /**
     * 设置 text
     *
     * @param text String
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CommonResultCodeEnum{" +
                "value=" + value +
                ", text='" + text + '\'' +
                '}';
    }
}
