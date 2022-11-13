package org.com.zlk.basic.dynamicproxy.cglib.demo1;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/11/11 11:02
 */
public class UserServiceImpl implements UserService{

    @Override
    public void say() {
        System.out.println("hello");
        say2();

    }

    @Override
    public void say2() {
        System.out.println("hello2");
    }

    /**
     * ，final，static等方法不会代码增强
     */
    public final void finalMethod() {
        System.out.println("final method");
    }

    public static void staticMethod() {
        System.out.println("static method");
    }

}
