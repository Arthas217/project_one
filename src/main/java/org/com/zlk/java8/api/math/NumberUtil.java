package org.com.zlk.java8.api.math;

import org.apache.commons.lang.StringUtils;


/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/7/22 17:06
 */
public class NumberUtil {

    /**
     * 将字符串解析成double类型数字，如果字符串为null、空字符串、非数字，则返回默认值
     *
     * @param str  需要解析的字符串
     * @param defaultValue 默认值
     * @return
     */
    public static double parseDouble(String str, double defaultValue) {
        if (StringUtils.isBlank(str)) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static void main(String[] args) {
        double v = parseDouble("1.2", 0.0);
        System.out.println(v);
    }
}
