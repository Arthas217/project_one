package org.com.zlk.java8.zzzz.zero;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/8/4 11:26
 */
public class DefaultMethodTest implements IAnimal, IDonkey, IHorse {


    public static void main(String[] args) {
        DefaultMethodTest dmt = new DefaultMethodTest();
        dmt.breath();
        dmt.run();

    }

    @Override
    public void run() {
        IAnimal.run();
        IHorse.super.run();
    }

}
