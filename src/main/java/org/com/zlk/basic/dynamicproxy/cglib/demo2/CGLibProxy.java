package org.com.zlk.basic.dynamicproxy.cglib.demo2;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/11/13 08:56
 */
public class CGLibProxy implements MethodInterceptor {

    // CGlib需要代理的目标对象
    private Object targetObject;

    public Object createProxyObject(Object obj) {
        this.targetObject = obj;
        //1.工具类
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(obj.getClass());
        //3.设置回调函数
        enhancer.setCallback(this);
        //4.创建子类(代理对象)
        Object proxyObj = enhancer.create();
        return proxyObj;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //模拟一下新增部分业务逻辑
        handle();
        System.out.println("这是增强方法前......");
        System.out.println("获取拦截方法：" + method.getName());
        System.out.println("获取方法参数：" + Arrays.toString(objects));
        System.out.println("MethodProxy：" + methodProxy);
        Object obj = method.invoke(targetObject, objects);
        System.out.println("这是增强方法后......");

        return obj;
    }

    private void handle(){
        //处理一些业务逻辑
        System.out.println("CGLibProxy处理业务逻辑");
    }

}
