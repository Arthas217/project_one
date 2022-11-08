package org.com.zlk.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 反射中构造、成员、方法使用
 * https://blog.csdn.net/sinat_38259539/article/details/71799078
 * @Date 2022/11/8 14:25
 */
public class JavaReflect2 {

    public static void main(String[] args) {
        try {

            testConstructReflect();
//            testMethodReflect();
//            testFXReflect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testConstructReflect() {
        try {
            //1.加载Class对象
            Class<?> c = Class.forName("org.com.zlk.basic.Student");
            //2.获取所有公有构造方法
            System.out.println("**********************所有公有构造方法*********************************");
            Constructor<?>[] constructors = c.getConstructors();
            for(Constructor cr: constructors){
                System.out.println(cr);
            }
            System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
            constructors = c.getDeclaredConstructors();
            for(Constructor r : constructors){
                System.out.println(r);
            }
            System.out.println("*****************获取公有、无参的构造方法*******************************");
//            Constructor con = c.getConstructor(null);
            Constructor con = c.getConstructor();
            System.out.println("con = " + con);
            //调用构造方法
            Object obj = con.newInstance();
            System.out.println("obj = " + obj);
            Student stu = (Student)obj;
            System.out.println(stu);
            System.out.println("******************获取私有构造方法，并调用*******************************");
            con = c.getDeclaredConstructor(char.class);//这个构造时哪里的？
            System.out.println(con);
            //调用构造方法
            con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
            obj = con.newInstance('男');
            System.out.println(obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void testFXReflect() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //通过反射越过泛型检查
        ArrayList<String> strList = new ArrayList<>();
        strList.add("aaa");
        strList.add("bbb");

        Class<? extends ArrayList> aClass = strList.getClass();
        Method m = aClass.getDeclaredMethod("add", Object.class);
        m.invoke(strList, 100);
        System.out.println(strList);
    }

    private static void testMethodReflect() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //1、获取JavaReflect对象的字节码
        Class clazz = Class.forName("org.com.zlk.basic.JavaReflect");
        //2、获取main方法
        Method methodMain = clazz.getMethod("main", String[].class);//第一个参数：方法名称，第二个参数：方法形参的类型，
        //3、调用main方法
        // methodMain.invoke(null, new String[]{"a","b","c"});
        //第一个参数，对象类型，因为方法是static静态的，所以为null可以，第二个参数是String数组，这里要注意在jdk1.4时是数组，jdk1.5之后是可变参数
        //这里拆的时候将  new String[]{"a","b","c"} 拆成3个对象。。。所以需要将它强转。
        methodMain.invoke(null, (Object) new String[]{"a", "b", "c"});//方式一
        // methodMain.invoke(null, new Object[]{new String[]{"a","b","c"}});//方式二
    }
}
