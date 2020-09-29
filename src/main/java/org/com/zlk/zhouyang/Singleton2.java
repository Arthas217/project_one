package org.com.zlk.zhouyang;

/**
 * @Author zc217
 * @Date 2020/9/29
 */
public class Singleton2 {

    private Singleton2() {
    }

    /**
     * 枚举类型是线程安全的并且只会装载一次
     */
    enum SingleEnum {

        INSTANCE;

        SingleEnum() {
            instance = new Singleton2();
        }

        Singleton2 instance;

        private Singleton2 getInstance() {
            return instance;
        }
    }

    public static Singleton2 getInstance() {
        return SingleEnum.INSTANCE.getInstance();
    }


    public static void main(String[] args) {
        Singleton2 instance = Singleton2.getInstance();
        Singleton2 instance2 = Singleton2.getInstance();
        System.out.println(instance == instance2);
    }
}
