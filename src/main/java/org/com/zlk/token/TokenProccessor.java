package org.com.zlk.token;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * https://blog.csdn.net/myjbase/article/details/91869360
 * @Author zc217
 * @Date 2020/11/26
 */
public class TokenProccessor {
    private TokenProccessor() {
    }

    private static final TokenProccessor instance = new TokenProccessor();

    public static TokenProccessor getInstance() {
        return instance;
    }

    /**
     * 基于时间+随机数规则生成的Token
     */

    public String getToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        System.out.println(token);
        try {
            // md5摘要
            MessageDigest md = MessageDigest.getInstance("md5");
            // 完成摘要计算
            byte md5[] = md.digest(token.getBytes());
            // BASE64编码器
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().getToken());
    }
}
