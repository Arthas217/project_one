package org.com.zlk.chxg.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;



/**
 * @author 会游泳的蚂蚁
 * @description: Byte-Buddy代理
 * @date 2023/9/1 09:05
 */
public class ByteBuddyProxy {

    public static <T> T getProxy(Class clazz) throws Exception {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(clazz)
                .method(ElementMatchers.<MethodDescription>named("queryUserInfo").and(ElementMatchers.takesArguments(0)))
                .intercept(MethodDelegation.to(InvocationHandler.class))
                .make();

        return (T) dynamicType.load(Thread.currentThread().getContextClassLoader()).getLoaded().newInstance();
    }

}
