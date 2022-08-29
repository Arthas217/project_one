package org.com.zlk.java8.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://mp.weixin.qq.com/s/qeB2Mf_aH0ln1sUnZri7rA
 * @Date 2022/8/29 11:58
 */
public class timeTrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(timeTrap.class);

    /**
     * Java日期处理的坑
     * @param args
     */
    public static void main(String[] args) {

        try {
            trap1();
            trap2();
            trap3();
            trap4();
//            trap5();
            trap6();
            trap7();
        } catch (ParseException e) {
            LOGGER.error("异常信息", e);
        }
    }


    /**
     * SimpleDateFormat的线性安全问题
     * 全局变量的 SimpleDateFormat，在并发情况下，存在安全性问题。
     * SimpleDateFormat 继承了 DateFormat；
     * DateFormat 类中维护了一个全局的 Calendar 变量；
     * sdf.parse(dateStr) 和 sdf.format(date)，都是由 Calendar 引用来储存的；
     * 如果 SimpleDateFormat 是 static 全局共享的，Calendar 引用也会被共享；
     * 又因为Calendar内部并没有线程安全机制，所以全局共享的 SimpleDateFormat 不是线性安全的。
     * 解决SimpleDateFormat 线性不安全问题，有三种方式：
     *  将SimpleDateFormat定义为局部变量；
     *  使用ThreadLocal；
     *  方法加同步锁 synchronized
     *
     *
     *  但是这个例子是什么鬼
     */
    private static void trap7() {
        // String to be scanned to find the pattern.
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(.*)(\\d+)(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
        } else {
            System.out.println("NO MATCH");
        }
    }

    /**
     *  Java日期的夏令时问题
     */
    private static void trap6() throws ParseException {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //时间被拨快了 1 个小时
        System.out.println("夏令时问题" + sdf.parse("1986-05-04 00:30:00"));

        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("夏令时问题" + sdf1.parse("1986-05-04 00:30:00"));
    }


    /**
     * SimpleDateFormat 解析的时间精度问题
     */
    private static void trap5() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = "2020-03";
        //Unparseable date: "2020-03",
        //SimpleDateFormat可以解析长于/等于它定义的时间精度，但是不能解析小于它定义的时间精度。
        System.out.println(sdf.parse(time));
    }

    /**
     * 日期本地化问题
     */
    private static void trap4() {
        //DateTimeFormatter 这个类默认进行本地化设置，如果默认是中文，解析英文字符串就会报异常。
        String dateStr = "Wed Mar 18 10:00:00 2020";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy");
//        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
//        System.out.println("日期本地化问题" + dateTime);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy", Locale.US);
        LocalDateTime dateTime1 = LocalDateTime.parse(dateStr, formatter1);
        System.out.println("日期本地化问题" + dateTime1);

        LocalDateTime now = LocalDateTime.now();
        String format = now.format(formatter);
        String format1 = now.format(formatter1);
        //星期一 八月 29 12:23:02 2022
        System.out.println(format);
        //Mon Aug 29 12:23:02 2022
        System.out.println(format1);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        String str2 = now.format(formatter2);
        String str3 = now.format(formatter3);
        System.out.println(str2);
        System.out.println(str3);


    }

    /**
     * Calendar 获取的月份比实际数字少 1 即 (0-11)
     */
    private static void trap3() {
        Calendar instance = Calendar.getInstance();
        System.out.println("当前" + instance.get(Calendar.MONTH) + "月份");
        System.out.println("当前" + (instance.get(Calendar.MONTH) + 1) + "月份");
    }

    /**
     * Java 日期格式化 YYYY 的坑
     * Java 日期格式化 hh 的坑
     * Java 日期格式化 DD 的坑
     */
    private static void trap2() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 31);
        Date testDate = calendar.getTime();
        // YYYY 是基于周来计算年的，它指向当天所在周属于的年份，一周从周日开始算起，周六结束，只要本周跨年，那么这一周就算下一年的了。
        SimpleDateFormat dtf = new SimpleDateFormat("YYYY-MM-dd");
        System.out.println("2019-12-31 转 YYYY-MM-dd 格式后 " + dtf.format(testDate));

        SimpleDateFormat dtf1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("2019-12-31 转 YYYY-MM-dd 格式后 " + dtf1.format(testDate));


        String str = "2020-03-18 12:00";
        //hh 是 12 制的日期格式，当时间为 12 点，会处理为 0 点。
        SimpleDateFormat dtf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        System.out.println(dtf2.parse(str));

        SimpleDateFormat dtf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(dtf3.parse(str));

        calendar.set(2019, Calendar.DECEMBER, 31);
        Date testDate1 = calendar.getTime();
        // DD 表示的是一年中的第几天
        SimpleDateFormat dtf4 = new SimpleDateFormat("yyyy-MM-DD");
        System.out.println("2019-12-31 转 yyyy-MM-DD 格式后 " + dtf4.format(testDate1));

        SimpleDateFormat dtf5 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("2019-12-31 转 yyyy-MM-DD 格式后 " + dtf5.format(testDate1));
    }

    /**
     * 1. 用 Calendar 设置时间的坑
     */
    private static void trap1() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, 10);
        //Calendar.HOUR 默认是按 12 小时制处理的 Mon Aug 29 22:00:10 CST 2022
        System.out.println(c.getTime());
        //Calendar.HOUR_OF_DAY，因为它才是按 24 小时处理的。Mon Aug 29 10:01:26 CST 2022
        c.set(Calendar.HOUR_OF_DAY, 10);
        System.out.println(c.getTime());
    }
}
