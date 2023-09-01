package org.com.zlk.chxg.proxy;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * @author 会游泳的蚂蚁
 * @description: Byte-Buddy代理使用这个类
 * @date 2023/9/1 09:22
 */
public class InvocationHandler {
    @RuntimeType
    public static Object intercept(@Origin Method method, @AllArguments Object[] args, @SuperCall Callable<?> callable) throws Exception {
        System.out.println(method.getName() + " 你被代理了，By Byte-Buddy！!!!");
        return callable.call();
    }
}
