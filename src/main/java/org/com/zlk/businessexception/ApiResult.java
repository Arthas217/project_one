package org.com.zlk.businessexception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 标准Api接口基本响应实体 1.0/1.1
 * @Date 2022/7/2 09:41
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ApiResult<T> implements Serializable {

    // add 1.0 version
    private static final long serialVersionUID = 5636013571993800949L;
    private int responseCode;
    private String responseMsg;
    private boolean isSuccess;
    private T data;

    // 以下 add 1.1 version  增加一些公用的处理方法
    public static ApiResult<String> success() {
        return success("success");
    }

    public static <T> ApiResult<T> success(T data) {
        return (new ApiResult()).setResponseCode(0).setResponseMsg("操作成功").setSuccess(true).setData(data);
    }

    public static <T> ApiResult<T> success(int code, String message, T data) {
        return (new ApiResult()).setResponseCode(code).setResponseMsg(message).setSuccess(true).setData(data);
    }

    public static ApiResult<String> fail() {
        return fail(-1);
    }

    public static ApiResult<String> fail(int code) {
        return fail(code, "fail");
    }

    public static <T> ApiResult<T> fail(T data) {
        return fail(-1, data);
    }

    public static <T> ApiResult<T> fail(int code, T data) {
        return (new ApiResult()).setResponseCode(code).setResponseMsg("操作失败").setSuccess(false).setData(data);
    }

    public static <T> ApiResult<T> fail(int code, String message, T data) {
        return (new ApiResult()).setResponseCode(code).setResponseMsg(message).setSuccess(false).setData(data);
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "responseCode=" + responseCode +
                ", responseMsg='" + responseMsg + '\'' +
                ", isSuccess=" + isSuccess +
                ", data=" + data +
                '}';
    }
}
