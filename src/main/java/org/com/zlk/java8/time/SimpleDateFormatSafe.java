package org.com.zlk.java8.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://mp.weixin.qq.com/s/qeB2Mf_aH0ln1sUnZri7rA
 * @Date 2022/8/29 12:57
 */
public class SimpleDateFormatSafe {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<>();
    private static ThreadLocal<Integer> num = new ThreadLocal<>();

    public static DateFormat getDateFormat() {
        DateFormat df = threadLocal.get();
        if (df == null) {
            df = new SimpleDateFormat(DATE_FORMAT);
            threadLocal.set(df);
        }
        return df;
    }

    public static String formatDate(Date date) throws ParseException {
        return getDateFormat().format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100,
                1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(500),
                new ThreadPoolExecutor.CallerRunsPolicy());
        while (true) {
            threadPoolExecutor.execute(() -> {
                try {
                    String dateString = formatDate(new Date());
                    Date parseDate = parse(dateString);
                    String dateString2 = formatDate(parseDate);
                    System.out.println(dateString.equals(dateString2) + String.valueOf(num.get()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
