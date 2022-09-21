package org.com.zlk.java8.book;

import java.util.Random;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 随机选择
 * @Date 2022/9/21 17:35
 */
public class EnumUtil {

    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}
