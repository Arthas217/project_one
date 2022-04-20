package org.com.zlk.code;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/4/19 17:15
 */
public class Pinyin {

    public static String stringToUnicode(String s) {
        try {
            StringBuffer out = new StringBuffer("");
            // 直接获取字符串的unicode二进制
            byte[] bytes = s.getBytes("unicode");
            // 然后将其byte转换成对应的16进制表示即可
            for (int i = 0; i < bytes.length - 1; i += 2) {
                out.append("\\u");
                String str = Integer.toHexString(bytes[i + 1] & 0xff);
                for (int j = str.length(); j < 2; j++) {
                    out.append("0");
                }
                String str1 = Integer.toHexString(bytes[i] & 0xff);
                out.append(str1);
                out.append(str);
            }
            return out.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            // group 6728
            String group = matcher.group(2);
            // ch:'木' 26408
            ch = (char) Integer.parseInt(group, 16);
            // group1 \u6728
            String group1 = matcher.group(1);
            str = str.replace(group1, ch + "");
        }
        return str;
    }

    public static void main(String[] args) {
        // \ud855\udd84\ud870\udc29\ud871\ude21
        System.out.println(unicodeToString("\\ud873\\udc56"));
        System.out.println(unicodeToString("\\ud855\\udd84"));
        System.out.println(unicodeToString("\\u25584"));
        String unicode = stringToUnicode(unicodeToString("\\ud873\\udc56"));

    }
}
