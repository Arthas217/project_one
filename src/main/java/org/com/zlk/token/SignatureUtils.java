package org.com.zlk.token;

import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 接口验证签名工具类
 *
 * @Date 2020/11/26
 */
public class SignatureUtils {
    /**
     * 应用唯一标识
     */
    private static final String APP_KEY = "appkey";
    /**
     * 应用秘钥
     */
    private static final String SECRET_KEY = "secretkey";
    /**
     * 时间戳
     */
    private static final String TIMESTAMP = "timestamp";
    /**
     * 随机字符串
     */
    private static final String NONCE_STR = "noncestr";

    //params: {noncestr=e697ea7d-27c3-42a5-a421-2aa3d7d58efc, timestamp=1590375647, signature=CC78E603495F5AB72727024F4EFF1168}

    /**
     * 验证签名算法
     *
     * @param params    第三方传递过来的验证签名的参数,包括签名、随机字符串、时间戳等
     * @param appKey    应用唯一标识
     * @param secretKey 应用秘钥
     * @return 状态码
     */
    public static CommonResultCodeEnum checkInterfaceSignature(Map<String, Object> params, String appKey, String secretKey) {
        // 1. 验证参数
        if (null == params || params.isEmpty() || StringUtils.isBlank(appKey) || StringUtils.isBlank(secretKey)) {
            return CommonResultCodeEnum.NULL_PARAMS_DATA;
        }

        //第三方传入的签名
        String signature = null != params.get("signature") ? params.get("signature").toString() : "";

        //2. 验证是否缺少验签所需参数
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();

        //缺少随机时间戳参数
        if (!keys.contains(TIMESTAMP)) {
            return CommonResultCodeEnum.MISSING_TIMESTAMP;
        }

        //缺少随机字符串参数
        if (!keys.contains(NONCE_STR)) {
            return CommonResultCodeEnum.MISSING_NONCESTR;
        }

        //循环校验参数的值是否为空
        while (iterator.hasNext()) {
            String paramName = iterator.next();
            Object paramValue = params.get(paramName);
            if ((TIMESTAMP.equals(paramName) || NONCE_STR.equals(paramName)) && null == paramValue) {
                return CommonResultCodeEnum.NULL_PARAMS_VALUE;
            }
        }

        String[] signatureParamsKeys = new String[]{APP_KEY, SECRET_KEY, TIMESTAMP, NONCE_STR};

        // 3.对参数进行排序
        sort(signatureParamsKeys);

        //组装参与签名生成的字符串(使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串)
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < signatureParamsKeys.length; i++) {
            if (APP_KEY.equals(signatureParamsKeys[i])) {
                if (StringUtils.isBlank(appKey)) {
                    return CommonResultCodeEnum.MISSING_APPKEY;
                } else {
                    string.append(signatureParamsKeys[i]).append("=").append(appKey);
                }
            } else if (SECRET_KEY.equals(signatureParamsKeys[i])) {
                if (StringUtils.isBlank(secretKey)) {
                    return CommonResultCodeEnum.MISSING_SECRETKEY;
                } else {
                    string.append(signatureParamsKeys[i]).append("=").append(secretKey);
                }
            } else {
                //拼接随机字符串、时间戳参数
                String paramValue = null != params.get(signatureParamsKeys[i]) ? params.get(signatureParamsKeys[i]).toString() : "";
                string.append(signatureParamsKeys[i]).append("=").append(paramValue);
            }

            if (i != signatureParamsKeys.length - 1) {
                string.append("&");
            }
        }

        // 4.生成加密签名
        String mySignature = MD5(string.toString()).toUpperCase();

        // 5.校验签名是否一致
        if (StringUtils.isNotBlank(mySignature) && mySignature.equals(signature)) {
            return CommonResultCodeEnum.SUCCESS;
        }
        return CommonResultCodeEnum.SIGNATURE_MISMATCH;
    }
    /**
     * 字典排序算法
     * @param strArr 待排序数组
     */
    public static void sort(String[] strArr) {
        for (int i = 0; i < strArr.length - 1; i++) {
            for (int j = i + 1; j < strArr.length; j++) {
                if (strArr[j].compareTo(strArr[i]) < 0) {
                    String temp = strArr[i];
                    strArr[i] = strArr[j];
                    strArr[j] = temp;
                }
            }
        }
    }
    /**
     * MD5加密
     * @param plainText 待加密字符串
     * @return
     */
    public static String MD5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuilder buf = new StringBuilder();
            for (byte b1 : b) {
                i = b1;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            // 32位加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取时间戳
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    /**
     * 获取随机字符串
     */
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<>();
        params.put("noncestr", "e697ea7d-27c3-42a5-a421-2aa3d7d58efc");
//        params.put("timestamp", "1590375647");
        params.put("timestamp", "1590375641");
        params.put("signature", "CC78E603495F5AB72727024F4EFF1168");
        //为了演示方便,正常项目中应该是发送HTTP请求进行调用，这里主要关注验证签名这一块
        CommonResultCodeEnum codeEnum = checkInterfaceSignature(params, "STUDENT_LIST_SAFE_INTERFACE_APP_KEY", "f1e4cd8ca987f11f2a773aa021316159");
        System.out.println(codeEnum.getText());
        System.out.println(codeEnum.getValue());

    }
}
