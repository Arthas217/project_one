package org.com.zlk.java8.api.math;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://mp.weixin.qq.com/s/2qwJun8t_-b849kU9Dvi7A
 * @Date 2022/4/12 12:26
 */
public class BigDecimalDemo {

    public static void main(String[] args) {
//        constructMethod();
//        compare();
        //格式化的货币值和百分比
//        formatMethod();
        //格式化结果保留2为小数，不足则补0
//        testValue();

        // divide方法进行除法时当不整除，出现无限循环小数.抛异常：java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        bug();

    }

    private static void bug() {
        // divide方法设置精确的小数点，如：divide(xxxxx,2)
        BigDecimal b1 = new BigDecimal("10");
        BigDecimal b2 = new BigDecimal("3");
        BigDecimal divide = b1.divide(b2,2,4);
        System.out.println(divide);
    }

    private static void testValue() {
        System.out.println(formatToNumber(new BigDecimal("3.435")));
        System.out.println(formatToNumber(new BigDecimal(0)));
        System.out.println(formatToNumber(new BigDecimal("0.00")));
        System.out.println(formatToNumber(new BigDecimal("0.001")));
        System.out.println(formatToNumber(new BigDecimal("0.006")));
        System.out.println(formatToNumber(new BigDecimal("0.206")));
    }

    private static void formatMethod() {
        //建立货币格式化引用
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        //建立百分比格式化引用
        NumberFormat percent = NumberFormat.getPercentInstance();
        // 百分比小数点最多3位
        percent.setMaximumFractionDigits(3);

        //贷款金额
        BigDecimal loanAmount = new BigDecimal("15000.48");
        //利率
        BigDecimal interestRate = new BigDecimal("0.008");
        //相乘利息
        BigDecimal interest = loanAmount.multiply(interestRate);

        System.out.println("贷款金额:\t" + currency.format(loanAmount));
        System.out.println("利率:\t" + percent.format(interestRate));
        System.out.println("利息:\t" + currency.format(interest));
    }

    private static void compare() {
        int i = new BigDecimal("0.1").compareTo(new BigDecimal(0.2));
        System.out.println(i);
    }

    private static void constructMethod() {
        //构造函数
        BigDecimal b1 = new BigDecimal(1);
        BigDecimal b2 = new BigDecimal(2D);
        BigDecimal b3 = new BigDecimal(3L);
        BigDecimal b4 = new BigDecimal("2");
        //有一定的不可预知性
        BigDecimal b5 = new BigDecimal(0.1);
        //完全可预知的
        BigDecimal b6 = new BigDecimal("0.1");
        System.out.println(b1);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b4);
        System.out.println(b5);
        System.out.println(b6);
    }


    /**
     *  传入的参数等于0，则直接返回字符串"0.00"
     *  0~1之间的BigDecimal小数，格式化后失去前面的0,则前面直接加上0。
     *  大于1的小数，直接格式化返回字符串
     */
    public static String formatToNumber(BigDecimal obj) {
        DecimalFormat df = new DecimalFormat("#.00");
        BigDecimal zero = BigDecimal.ZERO;
        if (obj.compareTo(zero) == 0) {
            return "0.00";
        } else if (obj.compareTo(zero) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
            return "0" + df.format(obj);
        } else {
            return df.format(obj);
        }
    }
}
