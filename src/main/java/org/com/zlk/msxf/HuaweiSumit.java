package org.com.zlk.msxf;

import com.alibaba.fastjson.JSONObject;
import org.com.zlk.msxf.http.HttpResult;
import org.com.zlk.msxf.http.JsonUtil;
import org.com.zlk.msxf.http.OkHttpUtil;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 会游泳的蚂蚁
 * @date 2024/3/25 16:03
 */
public class HuaweiSumit {

    private static final String ID = "659313182791187264";
    private static final String domain = "https://connect-api.cloud.huawei.com/api";
    private static final String SECRET = "860B644511130116C1263C1A123D2487BC984589502357FF34FED4D1DB7119D6";


    public static void main(String[] args) {
//        String token = getToken();
//        System.out.println(token);
        String token = "eyJraWQiOiJ4ZTVhNkhmdnRzdWxNTm4zaXZlaGh2Y1Y1cnVhbjljZiIsInR5cCI6IkpXVCIsImFsZyI6IkhTMjU2In0.eyJzdWIiOiI2NTkzMTMxODI3OTExODcyNjQiLCJkbiI6MSwiY2xpZW50X3R5cGUiOjEsImV4cCI6MTcxMTUyODA1MCwiaWF0IjoxNzExMzU1MjUwfQ.OZNxH_GgwoF2aEQV2ac7EPjNsqcmsEfRRR6no2fXL-g";
//        String appId = getAppId(token, "com.msxf.msg");
//        System.out.println(appId);
        String appId = "10716719";

    }


    public static String getToken() {
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credentials");
        params.put("client_id", ID);
        params.put("client_secret", SECRET);
        HttpResult httpResult = OkHttpUtil.defaultClient().doPostJson(domain + "/oauth2/v1/token", params, null);
        int httpCode = httpResult.getHttpCode();
        if (httpCode == HttpStatus.OK.value()) {
            String responseBody = httpResult.getResponseBody();
            LinkedHashMap<String, Object> map = JsonUtil.toMap(responseBody);
            return (String) map.get("access_token");
        }
        return "";
    }


    public static String getAppId(String token, String pkgName) {
        Map<String, String> headMap = new HashMap<>();
        headMap.put("client_id", ID);
        headMap.put("Authorization", "Bearer " + token);
        Map<String, String> params = new HashMap<>();
        params.put("packageName", pkgName);
        HttpResult httpResult = OkHttpUtil.defaultClient().doGet(domain + "/publish/v2/appid-list", params, headMap);
        int httpCode = httpResult.getHttpCode();
        if (httpCode == HttpStatus.OK.value()) {
            ArrayList arrayList = (ArrayList) JsonUtil.toMap(httpResult.getResponseBody()).get("appids");
            Map map = (Map) arrayList.get(0);
            return (String) map.get("value");
        }
        return "";
    }

}
