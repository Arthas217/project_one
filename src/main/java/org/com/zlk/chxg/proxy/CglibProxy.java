package org.com.zlk.chxg.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 会游泳的蚂蚁
 * @description: CGLIB代理（Spring、AOP切面、鉴权服务、中间件开发、RPC框架等）
 * @date 2023/9/1 00:08
 */
public class CglibProxy implements MethodInterceptor {

    public Object newInstance(Object obj){
        // this指代的就是CglibProxy对象
        return Enhancer.create(obj.getClass(),this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("我被CglibProxy代理了");
        return methodProxy.invokeSuper(o, objects);
    }
}
