package org.com.zlk.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/6/30 08:57
 */
public class StoreCouponUtil {

    private static final Long ZERO = 0L;
    private static final SimpleDateFormat SF = new SimpleDateFormat("HH:mm:ss");
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreCouponUtil.class);

    public static String simpleRmb(long penny) {
        long yLocation = penny / 100;
        long jLocation = penny % 100 / 10;
        long fLocation = penny % 100 % 10;
        String rmb = "";
        boolean fValue = ZERO.equals(fLocation);
        boolean jValue = ZERO.equals(jLocation);
        boolean yValue = ZERO.equals(yLocation);
        if (yValue && jValue && fValue) {
            rmb += 0;
        } else if (!yValue && jValue && fValue) {
            rmb += yLocation;
        } else if (fValue) {
            rmb += yLocation + "." + jLocation;
        } else {
            rmb += yLocation + "." + jLocation + fLocation;
        }
        return rmb;
    }


    public static boolean belongBetweenTime(String nowTime, String beginTime, String endTime) {
        Calendar now = Calendar.getInstance();
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        try {
            now.setTime(SF.parse(nowTime));
            begin.setTime(SF.parse(beginTime));
            end.setTime(SF.parse(endTime));
            return now.after(begin) && now.before(end);
        } catch (ParseException e) {
            LOGGER.error("-----belongBetweenTime异常---------",e);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(simpleRmb(1));
        System.out.println(simpleRmb(10));
        System.out.println(simpleRmb(0));
        System.out.println(simpleRmb(110));
        System.out.println(simpleRmb(101));
        System.out.println(simpleRmb(100));
        System.out.println(belongBetweenTime("12:00:00", "08:12:12", "23:00:12"));
    }

}
