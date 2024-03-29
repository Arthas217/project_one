package org.com.zlk.basic.dynamicproxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @Author 会游泳的蚂蚁
 * @Description: JDK动态代理
 * @Date 2022/11/10 17:34
 */
public class JDKDynamicProxyTest {

    public static void main(String[] args) {

        Class<?>[] interfaces = HelloImpl.class.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println(interfaces[i]);
        }
//        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
//        System.out.println(contextClassLoader);

        // 被调用者以接口为中心
        HelloImpl hello = new HelloImpl();
        MyInvocationHandler handler = new MyInvocationHandler(hello);
        // 构造代码实例
        ClassLoader classLoader = HelloImpl.class.getClassLoader();
        Hello proxyHello = (Hello) Proxy.newProxyInstance(classLoader, interfaces, handler);
        // 调用代理方法
        System.out.println("------------------调用invoke--------------");
        proxyHello.sayHello(123, "zlk");
    }
}
