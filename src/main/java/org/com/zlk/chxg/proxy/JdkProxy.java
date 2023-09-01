package org.com.zlk.chxg.proxy;

import java.lang.reflect.Proxy;

/**
 * @author 会游泳的蚂蚁
 * @description: JDK代理（中间件开发、设计模式中代理模式和装饰器模式应用）
 * @date 2023/9/1 00:09
 */
public class JdkProxy {

    public static <T> T getJDKProxy(Class<T> clazz){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return  (T) Proxy.newProxyInstance(classLoader, new Class[]{clazz}, (proxy, method, args) -> {
            System.out.println(method.getName() + " 你被代理了，By JDKProxy！");
            return "小傅哥，公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！";
        });
    }
}
