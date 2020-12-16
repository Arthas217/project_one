package org.com.zlk.zhouyang;

/**
 * @Author zc217
 * @Date 2020/12/16
 */
public class Singleton1 {

    public static Singleton1 getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final Singleton1 INSTANCE = new Singleton1();
    }
}
