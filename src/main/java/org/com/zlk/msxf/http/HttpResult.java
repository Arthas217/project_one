package org.com.zlk.msxf.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * http请求结果 通用封装类
 * @author 会游泳的蚂蚁
 * @date 2024/3/23 14:12
 */
@Data
@AllArgsConstructor
public class HttpResult {

    private int httpCode;
    private String responseBody;
    private boolean success;

    public HttpResult(int httpCode, String responseBody) {
        this.httpCode = httpCode;
        this.responseBody = responseBody;
        this.success = httpCode == HttpStatus.OK.value() || httpCode == HttpStatus.CREATED.value();
    }
}
