package org.com.zlk.code;

import org.apache.commons.lang.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/1/5 16:34
 */
public class CharUtil {

    /**
     * 十六进制字符串转为字节数据
     *
     * @param hexStr
     * @return
     */
    public static byte[] hex2Byte(String hexStr) {
        if (StringUtils.isBlank(hexStr)) {
            return new byte[0];
        }
        //转大写
        hexStr = hexStr.toUpperCase();
        char[] chars = hexStr.toCharArray();
        int len = hexStr.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (charToByte(chars[pos]) << 4 | charToByte(chars[pos + 1]) & 0xff);
        }
        return result;
    }

    /**
     * 二进制字节数组转为十六进制字符串
     * @param bytes
     * @return
     */
    public static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                stringBuilder.append(temp);
            }
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


    public static String sha256Encode(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
        return byte2Hex(messageDigest.digest());
    }


    public static void main(String[] args) {
        // Unicode
        String s = "\u0061";
        String s1 = "a";
        String s2 = "110100";
        try {
            System.out.println(sha256Encode(s2));
            System.out.println(byte2Hex(s1.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
