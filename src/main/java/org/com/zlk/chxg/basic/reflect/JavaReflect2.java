package org.com.zlk.chxg.basic.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 反射中构造、成员、方法使用
 * https://blog.csdn.net/sinat_38259539/article/details/71799078
 * @Date 2022/11/8 14:25
 */
public class JavaReflect2 {

    public static void main(String[] args) {
        try {
            //构造器
//            testConstructReflect();
            //成员变量
//            testFieldReflect();
            //成员方法
//            testMethodReflect();
            // 通过反射越过泛型检查
            testNoPassGenericityByReflect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testFieldReflect() {
        try {
            // 加载Class对象
            Class<?> stuClass = Class.forName("org.com.zlk.chxg.basic.reflect.Student");
            // 获取字段
            System.out.println("************获取所有公有的字段********************");
            Field[] fieldArray = stuClass.getFields();
            for (Field f : fieldArray) {
                System.out.println(f);
            }

            System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
            fieldArray = stuClass.getDeclaredFields();
            for (Field f : fieldArray) {
                System.out.println(f);
            }

            System.out.println("*************获取公有字段并调用***********************************");
            Field f = stuClass.getField("name");
            System.out.println(f);
            //调用了公有、无参构造方法获取一个对象
            Object obj = stuClass.getConstructor().newInstance();
            //为字段设置值
            f.set(obj, "刘德华");//为Student对象中的name属性赋值
            //验证
            Student stu = (Student) obj;
            System.out.println("验证实例：" + stu);


            System.out.println("**************获取私有字段并调用********************************");
            f = stuClass.getDeclaredField("phoneNum");
            System.out.println(f);
            f.setAccessible(true);//暴力解除私有限定
            f.set(obj, "18888889999");
            System.out.println("验证实例：" + stu);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void testConstructReflect() {
        try {
            //加载Class对象
            Class<?> c = Class.forName("org.com.zlk.chxg.basic.reflect.Student");
            //获取所有公有构造方法
            System.out.println("**********************获取所有公有构造方法*********************************");
            Constructor<?>[] constructors = c.getConstructors();
            for (Constructor cr : constructors) {
                System.out.println(cr);
            }
            System.out.println("************获取所有构造方法(包括：私有、受保护、默认、公有)***************");
            constructors = c.getDeclaredConstructors();
            for (Constructor r : constructors) {
                System.out.println(r);
            }
            System.out.println("*****************获取公有、无参的构造方法*******************************");
            Constructor con = c.getConstructor(null);
            Constructor con1 = c.getConstructor();
            System.out.println("公有、无参的构造方法是  参数是null " + con);
            System.out.println("公有、无参的构造方法是 " + con1);

            System.out.println("******************获取无参数构造方法，并调用实例化*******************************");
            Object obj = con.newInstance();
            System.out.println("调用无参数构造.Student()时，实例obj= " + obj);
            Student stu = (Student) obj;
            System.out.println("类型转换为Student=" + stu);

            System.out.println("*****************获取公有、参数类型为char的构造方法*******************************");
            Constructor con2 = c.getDeclaredConstructor(char.class);
            System.out.println(con2);
            System.out.println("******************从无参数构造函数获取有参数构造方法，并调用实例化*******************************");
            Object object = con2.newInstance('男');
            System.out.println(object);

            System.out.println("******************获取有参数私有构造方法，并调用实例化*******************************");
            Constructor con3 = c.getDeclaredConstructor(int.class);
            con3.setAccessible(true);
            Object newInstance = con3.newInstance(1);
            System.out.println(newInstance);

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


    private static void testMethodReflect() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 加载Class对象
        Class<?> stuClass = Class.forName("org.com.zlk.chxg.basic.reflect.Student");
        // 获取所有公有方法
        System.out.println("***************获取所有的”公有“方法*******************");
        stuClass.getMethods();
        Method[] methodArray = stuClass.getMethods();
        for (Method m : methodArray) {
            System.out.println(m);
        }

        System.out.println("***************获取所有的方法，包括私有的等*******************");
        methodArray = stuClass.getDeclaredMethods();
        for (Method m : methodArray) {
            System.out.println(m);
        }

        System.out.println("***************获取公有的show1()方法*******************");
        Method m = stuClass.getMethod("show1", String.class);
        System.out.println(m);
        //无参构造实例化Student对象
        Object instance = stuClass.getConstructor().newInstance();
        Object result = m.invoke(instance, "刘德华");
        System.out.println("show1方法返回值：" + result);

        System.out.println("***************获取私有的show4()方法******************");
        m = stuClass.getDeclaredMethod("show4", int.class);
        System.out.println(m);
        m.setAccessible(true);//解除私有限定
        Object result2 = m.invoke(instance, 20);//一个是要调用的对象（获取有反射），一个是实参
        System.out.println("show4方法返回值：" + result2);


        System.out.println("***************获取类JavaReflect中的main()方法******************");
        // 主方法main 获取JavaReflect对象的字节码
        Class clazz = Class.forName("org.com.zlk.chxg.basic.reflect.JavaReflect");
        // 获取main方法,其中第一个参数：方法名称，第二个参数：方法形参的类型，
        Method methodMain = clazz.getMethod("main", String[].class);
        // 调用main方法,第一个参数，对象类型，因为方法是static静态的，所以为null可以;第二个参数是String数组，这里要注意在jdk1.4时是数组，jdk1.5之后是可变参数
//        Object inv = methodMain.invoke(null, new String[]{"a", "b", "c"});//会报异常
        Object inv = methodMain.invoke(null, (Object) new String[]{"a", "b", "c"});//方式一
//        Object inv = methodMain.invoke(null, new Object[]{new String[]{"a","b","c"}});//方式二
        System.out.println("调用main方法的返回值：" + inv);

    }



    private static void testNoPassGenericityByReflect() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        List<String> strList = Arrays.asList("aa", "bb");// java.util.Arrays$ArrayList.add(java.lang.Object)
        List<String> strList = new ArrayList<>();
        Class<? extends List> aClass = strList.getClass();
        Method m = aClass.getDeclaredMethod("add", Object.class);
        Object obj = m.invoke(strList, 100);
        System.out.println(strList);
        System.out.println(obj);
    }
}
