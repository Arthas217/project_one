package org.com.zlk.basic.dynamicproxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @Author 会游泳的蚂蚁
 * @Description: JDK动态代理
 * @Date 2022/11/10 17:34
 */
public class JDKDynamicProxy {

    public static void main(String[] args) {
        // 被调用者以接口为中心
        HelloImpl hello = new HelloImpl();
        MyInvocationHandler handler = new MyInvocationHandler(hello);
        // 构造代码实例
        Hello proxyHello = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(),
                HelloImpl.class.getInterfaces(), handler);
        // 调用代理方法
        proxyHello.sayHello(1,"zlk");
    }
}
