package org.com.zlk.controlapi;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://mp.weixin.qq.com/s/vTsPsy5qaPrV9_DmEoNsfQ
 * 响应信息
 * @Date 2022/8/12 09:32
 */
public class Responses<T> {

    private String code;

    private String msg;

    private T result;

    public Responses() {
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getResult() {
        return this.result;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(T result) {
        this.result = result;
    }

    private Responses(ResponseCode code, String msg, T result) {
        this.code = code.getCode();
        this.msg = msg;
        this.result = result;
    }

    // 成功
    public static <T> Responses<T> success() {
        return new Responses(ResponseCode.SUCCESS, "", null);
    }

    public static <T> Responses<T> success(T result) {
        return new Responses(ResponseCode.SUCCESS, "", result);
    }

    public static <T> Responses<T> success(String msg, T result) {
        return new Responses(ResponseCode.SUCCESS, msg, result);
    }

    //失败
    public static <T> Responses<T> error(String msg) {
        return new Responses(ResponseCode.FAIL, msg, null);
    }

    public static <T> Responses<T> error(ResponseCode code) {
        return new Responses(code, code.getDefaultMsg(), null);
    }

    public static <T> Responses<T> error(ResponseCode code, String msg) {
        return new Responses(code, msg, null);
    }
}
