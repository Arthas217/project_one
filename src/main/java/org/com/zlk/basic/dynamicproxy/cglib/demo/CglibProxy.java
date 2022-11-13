package org.com.zlk.basic.dynamicproxy.cglib.demo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * @Author 会游泳的蚂蚁
 * @Description: cglib的动态代理（小傅哥）
 * https://bugstack.cn/md/java/core/2019-12-21-%5B%E6%9C%89%E7%82%B9%E5%B9%B2%E8%B4%A7%5DJDK%E3%80%81CGLIB%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86%E4%BD%BF%E7%94%A8%E4%BB%A5%E5%8F%8A%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90.html
 * @Date 2022/11/11 10:09
 */
public class CglibProxy implements MethodInterceptor {

    /**
     * 提供构造方法，生成CGLIB的代理类，回调this
     * @param object   被代理类
     * @return
     */
    public Object newInstance(Object object) {
        return Enhancer.create(object.getClass(), this);
    }

    /**
     * intercept可以进行方法的增强，处理相关业务逻辑
     * @param o  代理对象
     * @param method  753
     * @param objects  方法参数
     * @param methodProxy  755
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("我被CglibProxy代理了");
        return methodProxy.invokeSuper(o, objects);
    }
}
