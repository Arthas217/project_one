package org.com.zlk.java8.time;


import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://mp.weixin.qq.com/s/H7h8ffW9qT7XnWWx4vz1Kg
 * @Date 2022/8/15 12:28
 */
public class timeDemo {

    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        // jdk1.1  继承Calendar   java.util.Date设定为可变类型，以及SimpleDateFormat的非线程安全使其应用非常受限
//        testCalendar();
        /*
            java.time包下的所有类都是不可变类型而且线程安全。
            1	Instant	时间戳
            2	Duration	持续时间，时间差
            3	LocalDate 只包含日期，比如：2018-02-05
            4	LocalTime 只包含时间，比如：23:12:10
            5	LocalDateTime 包含日期和时间，比如：2018-02-05 23:14:21
            6	Period 时间段
            7	ZoneOffset 时区偏移量，比如：+8:00
            8	ZonedDateTime	带时区的时间
            9	Clock	时钟，比如获取目前美国纽约的时间
            10	java.time.format.DateTimeFormatter	时间格式化
        */


        testRI();
    }

    private static void testRI() {
        //Java 8中获取今天的日期
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期:" + today);

        //Java 8中获取年、月、日信息
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.println("year:" + year);
        System.out.println("month:" + month);
        System.out.println("day:" + day);

        // 处理特定日期--这个方法的好处是没再犯老API的设计错误，比如年度起始于1900，月份是从0开 始等等
        LocalDate date = LocalDate.of(2018, 2, 6);
        System.out.println("自定义日期:" + date);

        // Java 8中判断两个日期是否相等
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2018, 2, 5);
        if (date1.equals(date2)) {
            System.out.println("时间相等");
        } else {
            System.out.println("时间不等");
        }

        //Java 8中检查像生日这种周期性事件
        LocalDate dateNow = LocalDate.now();
        LocalDate datebirth = LocalDate.of(2022, 8, 16);
        MonthDay birthday = MonthDay.of(datebirth.getMonth(), datebirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(dateNow);
        if (currentMonthDay.equals(birthday)) {
            System.out.println("是你的生日");
        } else {
            System.out.println("你的生日还没有到");
        }

        //通过增加小时、分、秒来计算将来的时间很常见,提供了更好的plusHours()方法替换add()，并且是兼容的。
        //注意，这些方法返回一个全新的LocalTime实例，由于其不可变性，返回后一定要用变量赋值。
        LocalTime time = LocalTime.now();
        LocalTime newTime = time.plusHours(3);
        System.out.println("三个小时后的时间为:" + newTime);

        //Java 8如何计算一周后的日期.
        //LocalDate日期不包含时间信息，它的plus()方法用来增加天、周、月，ChronoUnit类声明了这些时间单位。
        //由于LocalDate也是不变类型，返回后一定要用变量赋值。
        LocalDate today1 = LocalDate.now();
        System.out.println("今天的日期为:" + today1);
        LocalDate nextWeek = today1.plus(1, ChronoUnit.WEEKS);
        LocalDate nextMonths = today1.plus(1, ChronoUnit.MONTHS);
        LocalDate nextYears = today1.plus(1, ChronoUnit.YEARS);
        System.out.println("一周后为:" + nextWeek);
        System.out.println("一个月后为:" + nextMonths);
        System.out.println("一年后为:" + nextYears);

        //Java 8计算一年前或一年后的日期
        LocalDate today2 = LocalDate.now();
        LocalDate previousYear = today2.minus(1, ChronoUnit.YEARS);
        System.out.println("一年前的日期 : " + previousYear);
        LocalDate nextYear = today2.plus(1, ChronoUnit.YEARS);
        System.out.println("一年后的日期:" + nextYear);

        //Java 8的Clock时钟类
        //增加了一个Clock时钟类用于获取当时的时间戳，或当前时区下的日期时间信息。
        //以前用到System.currentTimeInMillis()和TimeZone.getDefault()的地方都可用Clock替换。
        // Returns the current time based on your system clock and set to UTC.
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock.millis());

        // Returns time based on system clock zone
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + defaultClock.millis());

        //在Java 8中获取当前的时间戳
        //时间戳信息里同时包含了日期和时间，实际上Instant类确实等同于 Java 8之前的Date类，可互相转换
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp.toEpochMilli());

        //Java 8中如何使用预定义的格式化工具去解析或格式化日期
        String dayAfterTommorrow = "20220818";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dayAfterTommorrow + "  格式化后的日期为:  " + formatted);

        // Java判断日期是早于还是晚于另一个日期,LocalDate类有两类方法isBefore()和isAfter()用于比较日期。
        LocalDate today3 = LocalDate.now();
        System.out.println("当前时间:" + today3);
        LocalDate tomorrow = today3.plus(1, ChronoUnit.DAYS);
        if (tomorrow.isAfter(today3)) {
            System.out.println("之后的日期:" + tomorrow);
        }

        LocalDate yesterday = today3.minus(1, ChronoUnit.DAYS);
        if (yesterday.isBefore(today3)) {
            System.out.println("之前的日期:" + yesterday);
        }

        //Java 8中处理时区
        //现在有一系列单独的类如ZoneId来处理特定时区，ZoneDateTime类来表示某时区下的时间
        //Java 8以前都是 GregorianCalendar类来做的。
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);

        // 如何表示信用卡到期这类固定日期，答案就在YearMonth,另外实例的lengthOfMonth()方法可以返回当月的天数
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2019, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);

        //Java 8中检查闰年
        LocalDate today4 = LocalDate.now();
        if (today4.isLeapYear()) {
            System.out.println("This year is Leap year");
        } else {
            System.out.println(today4 + " is not a Leap year");
        }

        // 计算两个日期之间的天数、周数或月数,用java.time.Period类
//        LocalDate today5 = LocalDate.now();
//        LocalDate java8Release = LocalDate.of(2018, 12, 14);
//        System.out.println(java8Release);
//        System.out.println(today5);
//        Period periodToNextJavaRelease = Period.between(java8Release,today5);
//        System.out.println("Months left between today and Java 8 release : " + periodToNextJavaRelease.getMonths());


        //日期转字符串
        LocalDateTime date3 = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String str = date3.format(dateTimeFormatter1);
        System.out.println("日期转换为字符串:" + str);

        //字符串转日期
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDate localDate = LocalDate.parse(str, dateTimeFormatter2);
        System.out.println("字符串转为日期:" + localDate);

    }

    private static void testCalendar() {
        GregorianCalendar gc = new GregorianCalendar();
        System.out.println(gc.getTimeZone());
        System.out.println(gc.getTimeInMillis());
        System.out.println(gc.getTime());
        System.out.println("----------------");
        GregorianCalendar sgc = new GregorianCalendar(2022, 8, 15, 12, 12, 12);
        GregorianCalendar egc = new GregorianCalendar(2022, 9, 15, 12, 12, 12);
        System.out.println(sgc.getTime());
        System.out.println(egc.getTime());
        Date now = new Date();
        System.out.println(now.after(sgc.getTime()));
        System.out.println(now.before(egc.getTime()));

    }

    /**
     * 当前时间是否在时间段内（格式时分秒）
     *
     * @param now
     * @param start
     * @param end
     * @return
     */
    public static boolean belongBetweenTime(String now, String start, String end) {
        try {
            Calendar calendar = Calendar.getInstance();
            Calendar s = Calendar.getInstance();
            Calendar e = Calendar.getInstance();
            calendar.setTime(DF.parse(now));
            s.setTime(DF.parse(start));
            e.setTime(DF.parse(end));
            return calendar.after(s) && calendar.before(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
