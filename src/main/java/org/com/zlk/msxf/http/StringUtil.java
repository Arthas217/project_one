package org.com.zlk.msxf.http;


/**
 * @author 会游泳的蚂蚁
 * @date 2024/1/13 17:42
 */
public class StringUtil {

    public static boolean isEmpty(CharSequence str) {
        return null == str || str.length() == 0;
    }


    public static boolean isBlank(CharSequence str) {
        final int length;
        if ((null == str) || (length = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (false == isBlankChar(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBlankChar(char c) {
        return isBlankChar((int) c);
    }

    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c) ||
                Character.isSpaceChar(c) ||
                c == '\ufeff' ||
                c == '\u202a' ||
                c == '\u0000' ||
                c == '\u3164' ||
                c == '\u2800' ||
                c == '\u180e';
    }


    public static String defaultString(String origin) {
        return defaultString(origin, "");
    }

    public static String defaultString(String origin, String defaultStr) {
        return hasText(origin) ? origin : defaultStr;
    }

    public static boolean hasText(String str) {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumeric(String str) {
        if (null == str) {
            return false;
        } else {
            int sz = str.length();
            for (int i = 0; i < sz; i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }


    public static boolean startWithIgnoreCase(String str, String prefix) {
        return (str != null && prefix != null && str.length() >= prefix.length() &&
                str.regionMatches(true, 0, prefix, 0, prefix.length()));
    }


    public static String cutString(String str, int maxLength) {
        if (!StringUtil.hasText(str)) {
            return str;
        }
        if (maxLength <= 0) {
            return str;
        }
        return maxLength <= str.length() ? str.substring(0, maxLength) : str;
    }

}
