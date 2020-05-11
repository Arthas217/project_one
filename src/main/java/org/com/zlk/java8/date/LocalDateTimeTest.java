package org.com.zlk.java8.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTest {

    public static void main(String[] args) {

        // 一、获取当前此刻的时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getDayOfYear());

        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());

        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getDayOfMonth());

        System.out.println(localDateTime.getHour());
        System.out.println(localDateTime.getMinute());
        System.out.println(localDateTime.getSecond());


        // 二、构造一个指定年、月、日的时间
        LocalDateTime before = LocalDateTime.of(2019,1,1,6,0,0);
        System.out.println(before);


        // 三、修改日期
        LocalDateTime modify = LocalDateTime.now();
        System.out.println(modify);
        modify = modify.plusYears(2);
        System.out.println(modify);
        modify = modify.minusMonths(1);
        System.out.println(modify);


        // 四、格式化日期
        LocalDateTime rightnow = LocalDateTime.now();
        System.out.println(rightnow.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(rightnow.format(DateTimeFormatter.ISO_DATE));
        System.out.println(rightnow.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

        // 五、时间反解析
        LocalDateTime parseTime = LocalDateTime.parse("2000--01-02 12:23",DateTimeFormatter.ofPattern("yyyy--MM-dd HH:mm"));
        System.out.println(parseTime);


    }
}
