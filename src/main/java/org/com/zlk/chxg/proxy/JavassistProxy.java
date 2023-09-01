package org.com.zlk.chxg.proxy;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @author 会游泳的蚂蚁
 * @description: Javassist代理
 * @date 2023/9/1 08:57
 */
public class JavassistProxy extends ClassLoader{

    public static <T> T getProxy(Class clazz) throws Exception {

        ClassPool pool = ClassPool.getDefault();
        // 获取类
        CtClass ctClass = pool.get(clazz.getName());
        // 获取方法
        CtMethod ctMethod = ctClass.getDeclaredMethod("queryUserInfo");
        // 方法前加强
        ctMethod.insertBefore("{System.out.println(\"" + ctMethod.getName() + " 你被代理了，By Javassist\");}");

        byte[] bytes = ctClass.toBytecode();

        return (T) new JavassistProxy().defineClass(clazz.getName(), bytes, 0, bytes.length).newInstance();
    }

}
