package org.com.zlk.chxg.spring.beanregister;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author 会游泳的蚂蚁
 * @description: 代理Bean工厂
 * @date 2023/9/5 17:58
 */
public class ProxyBeanFactory implements FactoryBean {

    /**
     * 把Java代理的对象(代理类)放到FactoryBean#getObject中
     * 再从Spring中获取到的对象，就是我们的代理对象了。
     *
     * @return 返回bean实例对象
     * @throws Exception
     */
    @Override
    public Object getObject() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //代理类实现
        InvocationHandler handler = (proxy, method, args) -> "被代理接口方法名：" + method.getName();
        return Proxy.newProxyInstance(classLoader, new Class[]{IUserDao.class}, handler);
    }

    /**
     * 返回实例类类型
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }
}
