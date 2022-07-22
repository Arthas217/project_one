package org.com.zlk.util;

import jodd.util.StringUtil;
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
        if(StringUtils.isBlank(str)){
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
}
