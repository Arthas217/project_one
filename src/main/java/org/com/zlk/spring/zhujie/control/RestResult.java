package org.com.zlk.spring.zhujie.control;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/20 09:18
 */
public class RestResult {

    private String  code;
    private String message;
    private ErrorMessage errorMessage;

    public RestResult(String code, String message, ErrorMessage errorMessage) {
        this.code = code;
        this.message = message;
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
