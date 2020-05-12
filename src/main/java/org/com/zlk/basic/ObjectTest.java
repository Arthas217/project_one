package org.com.zlk.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObjectTest implements Cloneable{

    private int age;
    private String name;

    // registerNatives()
    // 当有程序调用到native方法时， JVM去找到这些底层的方法进行调用
    // Object使用registerNatives()向JVM注册native方法(本地方法，由JVM实现，底层是 C/C++实现的)

    // 为什么要使用静态方法，还要放到静态块中呢？
    // 保证父类的类变量及方法的初始化一定先于子类 所以当子类调用相应native方法，比如hashCode，一定可以保证能够调用到JVM的native方法。
    // 因为Object是java所有类的基类，是整个类继承结构的顶端，也是最抽象的一个类

    public static void main(String[] args) throws CloneNotSupportedException {

        Object object = new Object();
        // 当前类的对象在运行时类的所有信息的集合
        Class<?> aClass = object.getClass();
        System.out.println(aClass);

        // 返回当前对象的 hashCode值
        // 整数范围内的 （-2^31~2^31-1）数字
        int codeValue = object.hashCode();
        System.out.println(codeValue);

        // 比较当前对象与目标对象是否相等，默认是比较引用是否指向同一对象
        System.out.println(object.equals(object));

        // clone()此方法返回当前对象的一个副本。需要实现 Cloneable接口
        // 标记接口，如果没有实现，当调用 object.clone()方法，会抛出 CloneNotSupportedException。
        ObjectTest objectTest = new ObjectTest(1,"TEST");
        ObjectTest clone = (ObjectTest) objectTest.clone();

        System.out.println(objectTest.equals(clone));
        System.out.println(objectTest.getAge() == clone.getAge());
        // clone的对象是一个新的对象；但原对象与 clone对象的 String类型的 name却是同一个引用（浅拷贝）
        System.out.println(objectTest.getName() == clone.getName());

    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "ObjectTest{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
