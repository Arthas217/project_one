package org.com.zlk.util;

import org.apache.commons.lang.StringUtils;


/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/7/20 16:37
 */
public class ZeroUtil {

    /**
     * 左补零、右补零
     *
     * @param str       要补全字符串
     * @param strLength 设定固定长度值
     * @return
     */
    public static String addZeroForNum(String str, int strLength) {
        //考虑下如果是空的情况，不做处理
        if(StringUtils.isBlank(str) || "0".equals(str)){
            return "";
        }
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                // sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    public static String delLeftZero(String str){
//        return str.replaceAll("^(0+)","");
        return str.replaceAll("^0*","");
    }

    public static void main(String[] args) {
        // "0" -> "0000"  工具类
        System.out.println(StringUtils.leftPad("0", 4, "0"));
        System.out.println(addZeroForNum("4",4));
        System.out.println(addZeroForNum(null,4));
        System.out.println(addZeroForNum("0",4));

        System.out.println(delLeftZero("0001"));
        System.out.println(delLeftZero("001"));
        System.out.println(delLeftZero("01"));
        System.out.println(delLeftZero("010"));
    }
}
