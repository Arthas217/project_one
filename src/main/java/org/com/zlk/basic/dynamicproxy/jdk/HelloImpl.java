package org.com.zlk.basic.dynamicproxy.jdk;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/11/10 17:30
 */
public class HelloImpl implements Hello {
    @Override
    public void sayHello(int a, String b) {
        System.out.println("HelloImpl类 sayHello方法输出 Hello World " + a +" "+ b);
    }
}
