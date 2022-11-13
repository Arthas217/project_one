package org.com.zlk.basic.dynamicproxy.cglib.demo1;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * https://blog.csdn.net/NCS123456/article/details/125225705
 * @Date 2022/11/11 11:06
 */
public class CglibTest1 {
    public static void main(String[] args) {
        UserServiceImpl target = new UserServiceImpl();

        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "src/main/java/org/com/zlk/basic/dynamicproxy/cglib/demo1/code");
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(UserServiceImpl.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new MyInterceptor(target));
        // 创建代理对象
        UserServiceImpl userService = (UserServiceImpl)enhancer.create();
        // 通过代理对象调用目标方法
        userService.say();
        userService.finalMethod();
        userService.staticMethod();

    }
}
