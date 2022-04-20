package org.com.zlk.java8.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/8/4 16:34
 */
public class DateTime {
    public static void main(String[] args) {

        // 老版本
        System.out.println(System.currentTimeMillis());

        //1、Clock替代版本
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock);
        long millis = clock.millis();
        System.out.println(millis);


        Instant instant = clock.instant();
        System.out.println(instant);

        // 方便地转换成老版本中的 java.util.Date 对象。
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);


        //2、Timezones 时区
        System.out.println(ZoneId.getAvailableZoneIds());
        // prints all available timezone ids

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        ZoneId zone3 = ZoneId.of("Asia/Shanghai");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
        System.out.println(zone3.getRules());

        // 3、 LocalTime  final类型对象
        ZoneId zone11 = ZoneId.of("Asia/Shanghai");
        ZoneId zone22 = ZoneId.of("Europe/London");
        LocalTime now1 = LocalTime.now(zone11);
        LocalTime now2 = LocalTime.now(zone22);
        System.out.println(now1);
        System.out.println(now2);
        System.out.println(now1.isBefore(now2));

        // 4、LocalDate  final类型对象
        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);

        // 2014年七月的第四天
        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();

        System.out.println(tomorrow);
        System.out.println(yesterday);
        System.out.println(independenceDay);
        System.out.println(dayOfWeek);


        // 直接解析日期字符串
        DateTimeFormatter germanFormatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM)
                .withLocale(Locale.GERMAN);
        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
        System.out.println(xmas);   // 2014-12-24



        // 5、 LocalDateTime 是一个日期-时间对象
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        DayOfWeek dayOfWeek1 = sylvester.getDayOfWeek();
        Month month = sylvester.getMonth();


        System.out.println(sylvester);
        System.out.println(dayOfWeek1);
        System.out.println(month);

        // 格式化 LocalDateTime 对象
        String str = "2020-04-13 18:49:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsedDate = LocalDateTime.parse(str, formatter);
        System.out.println(parsedDate);

        // 定时7点 可用于缓存key，设置当天数据过期，定时失效
        expireTimeClear();

    }

    private static void expireTimeClear() {
        // 时间间隔
        long l = Duration.between(LocalTime.now().withNano(0), LocalTime.of(10, 46, 0, 0)).toMillis();
        if (l < 0) {
            Calendar ca = Calendar.getInstance();
            //失效的时间
            ca.set(Calendar.HOUR_OF_DAY, 7);
            ca.set(Calendar.MINUTE, 0);
            ca.set(Calendar.SECOND, 0);
            l = ca.getTimeInMillis();
        }
        System.out.println(l);
    }
}
