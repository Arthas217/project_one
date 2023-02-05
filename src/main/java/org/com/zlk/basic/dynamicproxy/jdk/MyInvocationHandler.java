package org.com.zlk.basic.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author 会游泳的蚂蚁
 * @Description: JDK Proxy 利用Reflect
 * @Date 2022/11/10 17:31
 */
public class MyInvocationHandler implements InvocationHandler {

    /**
     * 被调用目标
     */
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * @param proxy 代理类，代理的类这里是HelloImpl
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //代理proxy为应用插入额外逻辑（这里是println）提供了便利的入口。
        System.out.println("重写 InvocationHandler invoke方法  Invoking sayHello");
        // 可以对方法进行增强。
        Object result = method.invoke(target, args);
        return result;
    }
}
