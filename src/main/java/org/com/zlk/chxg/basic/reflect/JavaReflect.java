package org.com.zlk.chxg.basic.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author 会游泳的蚂蚁
 * @Description: java反射原理 https://www.cnblogs.com/yougewe/p/10125073.html
 * @Date 2022/11/8 10:03
 */
public class JavaReflect {

    public void sayHello(String word) {
        System.out.println("你好," + word);
    }

    public static void main(String[] args) {
        try {
            // 使用外部配置的实现，进行动态加载类
            JavaReflect javaReflect = (JavaReflect) Class.forName("org.com.zlk.chxg.basic.reflect.JavaReflect").newInstance();
            javaReflect.sayHello("直接调用sayHello方法");
            // 根据配置的函数名，进行方法调用（不需要通用的接口抽象）
            Object t2 = new JavaReflect();
            Method method = t2.getClass().getDeclaredMethod("sayHello", String.class);
            method.invoke(t2, "invoke调用sayHello方法");
            for (String arg : args) {
                System.out.println("打印JavaReflect类main函数的参数值" + arg);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
