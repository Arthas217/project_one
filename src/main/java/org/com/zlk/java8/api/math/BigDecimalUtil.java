package org.com.zlk.java8.api.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/7/22 17:01
 */
public class BigDecimalUtil {

    /**
     * 两个BigDecimal相除，四舍五入保留两位小数
     * @param dividend
     * @param divisor
     * @return
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        if (dividend == null || divisor == null) {
            return null;
        }
        return dividend.divide(divisor, 2, BigDecimal.ROUND_HALF_DOWN);
    }


    /**
     * 将一个double数以四舍五入的方式保留2位，然后转换成String
     *
     * @param var
     *
     * @return
     */
    public static String decimal2String(Double var) {
        if (var != null) {
            BigDecimal bigDecimal = new BigDecimal(var).setScale(2, BigDecimal.ROUND_HALF_UP);
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(bigDecimal);
        }
        return "--";
    }


    /**
     * 将一个BigDecimal四舍五入保留两位小数，然后小数点后移两位，将其转成整数
     *
     * @param bigDecimal
     *
     * @return
     */
    public static BigInteger decimal2Integer(BigDecimal bigDecimal) {
        return bigDecimal != null ? bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).movePointRight(2).toBigInteger() : null;
    }

    /**
     * 将一个BigDecimal数以四舍五入的方式保留2位，然后转换成String
     *
     * @param bigDecimal
     *
     * @return
     */
    public static String decimal2String(BigDecimal bigDecimal) {
        return bigDecimal != null ? bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString() : "--";
    }


    /**
     * 将一个double数以四舍五入的方式保留3位，然后转换成String
     *
     * @param var
     *
     * @return
     */
    public static String decimal3String(Double var) {
        if (var != null) {
            BigDecimal bigDecimal = new BigDecimal(var).setScale(3, BigDecimal.ROUND_HALF_UP);
            DecimalFormat df = new DecimalFormat("0.000");
            return df.format(bigDecimal);
        }
        return "--";
    }

    /**
     * 将一个float数以四舍五入的方式保留2位，然后转换成String
     *
     * @param var
     *
     * @return
     */
    public static String decimal2String(Float var) {
        if (var != null) {
            BigDecimal bigDecimal = new BigDecimal(var).setScale(2, BigDecimal.ROUND_HALF_UP);
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(bigDecimal);
        }
        return "--";
    }

    /**
     * 将一个float数以四舍五入的方式保留2位，然后转换成String
     *
     * @param var
     *
     * @return
     */
    public static String decimal3String(Float var) {
        if (var != null) {
            BigDecimal bigDecimal = new BigDecimal(var).setScale(3, BigDecimal.ROUND_HALF_UP);
            DecimalFormat df = new DecimalFormat("0.000");
            return df.format(bigDecimal);
        }
        return "--";
    }

    /**
     * 将一个float数以四舍五入的方式保留4位，然后转换成String,若float不存在，返回0.0000
     *
     * @param var
     *
     * @return
     */
    public static String decimal4String(Float var) {
        if (var != null) {
            BigDecimal bigDecimal = new BigDecimal(var).setScale(4, BigDecimal.ROUND_HALF_UP);
            DecimalFormat df = new DecimalFormat("0.0000");
            return df.format(bigDecimal);
        }
        return "0.0000";
    }

    /**
     * 将一个double取掉小数位，然后转换成String
     *
     * @param var
     *
     * @return
     */
    public static String decimalNoPoint(Double var) {
        if (var != null) {
            Long bigDecimal = new BigDecimal(var).longValue();
            return bigDecimal.toString();
        }
        return "--";
    }

    /**
     * 将一个double数以四舍五入的方式保留2位
     *
     * @param var
     *
     * @return
     */
    public static double decimal2Double(Double var) {
        if (var != null) {
            return new BigDecimal(var).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return 0.0D;
    }

    /**
     * 将一个float数以四舍五入的方式保留2位
     *
     * @param var
     *
     * @return
     */
    public static float decimal2Float(Float var) {
        if (var != null) {
            return new BigDecimal(var).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        }
        return 0.0F;
    }


    /**
     * Long型转换  亿 || 万 带单位
     */
    public static String long2Turnover(Long var) {
        BigDecimal bigDecimal;
        BigDecimal divider;
        if (var != null) {
            bigDecimal = new BigDecimal(var);
            if (var > 100000000) {
                divider = new BigDecimal(100000000);
                bigDecimal = bigDecimal.divide(divider, 2, BigDecimal.ROUND_HALF_UP);
                return bigDecimal + "亿";
            } else if (var >= 1000000) { //上百万不要小数点
                divider = new BigDecimal(10000);
                bigDecimal = bigDecimal.divide(divider, 0, BigDecimal.ROUND_HALF_UP);
                return bigDecimal + "万";
            } else if (var >= 10000) {
                divider = new BigDecimal(10000);
                bigDecimal = bigDecimal.divide(divider, 2, BigDecimal.ROUND_HALF_UP);
                return bigDecimal + "万";
            } else {
                return var.toString();
            }
        }
        return "--";
    }

    //成交量根据version返回，如果是1.0，单位是手，如果是2.0，返回是股
    public static String getVolume(int version, long volume) {
        if (version == 1) {
            return long2Turnover(volume);
        } else {
            return long2Turnover(volume / 100);
        }
    }

    /**
     * Long型转换  亿 || 万  输出为整形，不带单位
     */
    public static int long2IntTurnover(Long var) {
        BigDecimal bigDecimal;
        BigDecimal divider;
        if (var != null) {
            bigDecimal = new BigDecimal(var);
            if (var > 100000000) {
                divider = new BigDecimal(100000000);
                bigDecimal = bigDecimal.divide(divider, 0, BigDecimal.ROUND_HALF_UP);
                return bigDecimal.intValue();
            } else if (var > 10000 && var <= 100000000) {
                divider = new BigDecimal(10000);
                bigDecimal = bigDecimal.divide(divider, 0, BigDecimal.ROUND_HALF_UP);
                return bigDecimal.intValue();
            } else {
                return var.intValue();
            }
        }
        return 0;
    }

    /**
     * K线的成交量统一转化成 单位为：万手
     */
    public static String long2StringTurnover(double var) {
        BigDecimal bigDecimal = new BigDecimal(var);
        BigDecimal divider = new BigDecimal(10000);
        return bigDecimal.divide(divider).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
    }

    //成交量根据version返回，如果是1.0，单位是手，如果是2.0，返回是股
    public static double getDoubleVolume(int version, double volume) {
        if (version == 1) {
            return new Double(long2StringTurnover(volume));
        } else {
            return new Double(long2StringTurnover(volume / 100));
        }
    }

//    public static Long str2Long(String va1) {
//        return NumberUtil.isNumber(va1) ? Long.parseLong(va1) : null;
//    }
//
//    public static long str2long(String va1) {
//        return NumberUtil.isNumber(va1) ? Long.parseLong(va1) : 0L;
//    }

    public static void main(String[] args) {
        System.out.println(divide(new BigDecimal(89), new BigDecimal(90)).floatValue());
        //小数点右移两位
        System.out.println(divide(new BigDecimal(89), new BigDecimal(90)).movePointRight(2).intValue());


        System.out.println(decimal2String(0.0001d * 100));
        System.out.println(decimal2Double(0.10D));
        System.out.println(decimal2Float(0.10F));

        double var = -0.0178;
        BigDecimal bigDecimal = new BigDecimal(var);
        System.out.println(decimal2String(var));
        System.out.println(decimal2String(bigDecimal));
        System.out.println(long2Turnover(123456789101L));
        System.out.println(getDoubleVolume(1,22333333.5564d));
        System.out.println(long2Turnover(1234567L));
        System.out.println(decimalNoPoint(1233556565.5665));
        System.out.println("成交量：" + getVolume(1, 212232323L) + "手");
        System.out.println("成交量：" + getVolume(2, 212232323L) + "股");
    }
}
