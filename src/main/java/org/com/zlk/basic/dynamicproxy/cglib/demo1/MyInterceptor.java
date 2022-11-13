package org.com.zlk.basic.dynamicproxy.cglib.demo1;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 方法拦截器
 * MethodInterceptor类似于jdk动态代理的InvocationHandler，方法调用前后进行增强。
 * @Date 2022/11/11 11:03
 */
public class MyInterceptor implements MethodInterceptor {

    /**
     * 被代理对象（目标对象）
     */
    private Object target;

    /**
     * 构造函数
     * @param target
     */
    public MyInterceptor(Object target) {
        this.target = target;
    }

    /**
     * @param o  代理对象
     * @param method 被代理对象的方法
     * @param objects  方法入参
     * @param methodProxy 代理方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib before");

        /**
         * 调用代理类FastClass对象
         *     cglib before
         *     hello
         *     cglib before
         *     hello2
         *     cglib after
         *     cglib after
         */
        Object result =  methodProxy.invokeSuper(o, objects);
        /**
         *     cglib before
         *     hello
         *     hello2
         *     cglib after
         */
//        Object result = methodProxy.invoke(target, objects);
        System.out.println("cglib after");
        return result;
    }






}
