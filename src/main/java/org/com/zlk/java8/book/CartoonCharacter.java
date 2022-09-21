package org.com.zlk.java8.book;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 枚举类型可以实现接口
 * @Date 2022/9/21 17:21
 */
public enum CartoonCharacter implements Supplier<CartoonCharacter> {

    SLAPPY, SPANKY, PUNCHY,
    SILLY, BOUNCY, NUTTY, BOB;

    private Random rand = new Random(47);

    @Override
    public  CartoonCharacter get() {
        return this.values()[rand.nextInt(values().length)];
    }
}
