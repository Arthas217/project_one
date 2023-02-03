package org.com.zlk.basic.reflect;

import org.com.zlk.basic.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
//            testConstructReflect();
//            testFieldReflect();
            testMethodReflect();
//            testFXReflect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testFieldReflect() {
        //1.加载Class对象
        try {
            Class<?> stuClass = Class.forName("org.com.zlk.basic.Student");
            //2.获取字段
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


            System.out.println("*************获取公有字段**并调用***********************************");
            Field f = stuClass.getField("name");
            System.out.println(f);
            //获取一个对象
            Object obj = stuClass.getConstructor().newInstance();//调用了公有、无参构造方法》Student stu = new Student();
            //为字段设置值
            f.set(obj, "刘德华");//为Student对象中的name属性赋值--》stu.name = "刘德华"
            //验证
            Student stu = (Student) obj;
            System.out.println("验证姓名：" + stu.name);


            System.out.println("**************获取私有字段****并调用********************************");
            f = stuClass.getDeclaredField("phoneNum");
            System.out.println(f);
            f.setAccessible(true);//暴力反射，解除私有限定
            f.set(obj, "18888889999");
            System.out.println("验证电话：" + stu);

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
            //1.加载Class对象
            Class<?> c = Class.forName("org.com.zlk.basic.Student");
            //2.获取所有公有构造方法
            System.out.println("**********************所有公有构造方法*********************************");
            Constructor<?>[] constructors = c.getConstructors();
            for (Constructor cr : constructors) {
                System.out.println(cr);
            }
            System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
            constructors = c.getDeclaredConstructors();
            for (Constructor r : constructors) {
                System.out.println(r);
            }

            System.out.println("*****************获取公有、无参的构造方法*******************************");
//            Constructor con = c.getConstructor(null);
            Constructor con = c.getConstructor();
            System.out.println("con = " + con);
            //调用构造方法
            Object obj = con.newInstance();
            System.out.println("obj = " + obj);
            Student stu = (Student) obj;
            System.out.println(stu);

            System.out.println("---------------------------------");
            con = c.getDeclaredConstructor(char.class);
            System.out.println(con);
            System.out.println("******************获取私有构造方法，并调用*******************************");
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

    /**
     * 通过反射越过泛型检查
     */
    private static void testFXReflect() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("aaa");
        strList.add("bbb");

        Class<? extends ArrayList> aClass = strList.getClass();
        Method m = aClass.getDeclaredMethod("add", Object.class);
        m.invoke(strList, 100);
        System.out.println(strList);
    }

    private static void testMethodReflect() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //1.加载Class对象
        Class<?> stuClass = Class.forName("org.com.zlk.basic.Student");
        //2.获取所有公有方法
        System.out.println("***************获取所有的”公有“方法*******************");
        stuClass.getMethods();
        Method[] methodArray = stuClass.getMethods();
        for (Method m : methodArray) {
            System.out.println(m);
        }

        System.out.println("***************获取所有的方法，包括私有的*******************");
        methodArray = stuClass.getDeclaredMethods();
        for (Method m : methodArray) {
            System.out.println(m);
        }


        System.out.println("***************获取公有的show1()方法*******************");
        Method m = stuClass.getMethod("show1", String.class);
        System.out.println(m);
        //实例化一个Student对象
        Object obj = stuClass.getConstructor().newInstance();
        m.invoke(obj, "刘德华");

        System.out.println("***************获取私有的show4()方法******************");
        m = stuClass.getDeclaredMethod("show4", int.class);
        System.out.println(m);
        m.setAccessible(true);//解除私有限定
        Object result = m.invoke(obj, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
        System.out.println("返回值：" + result);

        //测试主方法main
        //1、获取JavaReflect对象的字节码
        Class clazz = Class.forName("org.com.zlk.basic.reflect.JavaReflect");
        //2、获取main方法
        Method methodMain = clazz.getMethod("main", String[].class);//第一个参数：方法名称，第二个参数：方法形参的类型，
        //3、调用main方法
        // methodMain.invoke(null, new String[]{"a","b","c"});
        //第一个参数，对象类型，因为方法是static静态的，所以为null可以
        //第二个参数是String数组，这里要注意在jdk1.4时是数组，jdk1.5之后是可变参数

        //这里拆的时候将  new String[]{"a","b","c"} 拆成3个对象。。。所以需要将它强转。
        methodMain.invoke(null, (Object) new String[]{"a", "b", "c"});//方式一
        // methodMain.invoke(null, new Object[]{new String[]{"a","b","c"}});//方式二
    }
}
