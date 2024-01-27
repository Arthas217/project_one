package org.com.zlk.genericstype.common;

import org.com.zlk.model.Base;
import org.com.zlk.model.Sub;

import java.util.*;

/**
 * 泛型：能广泛适用的类型
 * 作用：参数化类型（ 类型当作参数传递给一个类或者是方法）
 * T 代表一般的任何类。
 * E 代表 Element 的意思，或者 Exception 异常的意思。
 * K 代表 Key 的意思。
 * V 代表 Value 的意思，通常与 K 一起配合使用。
 * S 代表 Subtype 的意思。
 */
public class GenericsStudy {

    public static void main(String[] args) {
        //泛型类
        generic_class();

        //泛型方法
        generic_method();

        //泛型类中的泛型方法
        generic_class_generic_method();

        //泛型接口
        generic_interface();

        //通配符
        generic_mix();

    }


    private static void generic_class() {
        // string
        GenericsClass<String> stringGenericsClass = new GenericsClass<>();
        stringGenericsClass.setField("abc");
        System.out.println(stringGenericsClass.getField());
        // int
        GenericsClass<Integer> integerGenericsClass = new GenericsClass<>();
        integerGenericsClass.setField(1);
        System.out.println(integerGenericsClass.getField());
        // Base和Sub依赖关系
        GenericsClass1<Base, Sub> baseSubGenericsClass = new GenericsClass1();
        System.out.println(baseSubGenericsClass.getValue1());
        System.out.println(baseSubGenericsClass.getValue2());
    }


    private static void generic_method() {
        GenericsMethod genericsMethod = new GenericsMethod();
        genericsMethod.method("String type");
    }

    private static void generic_class_generic_method() {
        GenericsMethodClass<String> genericsMethodClass = new GenericsMethodClass();
        genericsMethodClass.method1("泛型类中的类型参数T与泛型方法中的类型参数T是没有相应的联系的");
        System.out.println(genericsMethodClass.method2(10));
    }

    private static void generic_interface() {
        //泛型接口的实现 输出t值
        GenericsInterface<Double> genericsInterface = d -> System.out.println(d);
        genericsInterface.method1(1000d);
    }


    private static void generic_mix() {
        List<Base> baseList = new ArrayList<>();
        baseList.add(new Base(100));
        List<Sub> subList = new ArrayList<>();
        Sub sub1 = Sub.builder().value(2).num(4).build();
        Sub sub2 = Sub.builder().value(22).num(44).build();
        subList.add(sub1);
        subList.add(sub2);
        mix(baseList, subList);
    }

    private static <T> void mix(T t, Collection<? extends T> collection) {
        System.out.println(t);
        Iterator<? extends T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }




    /*
     通配符 <?> 无限定的通配符。
     Collection 对象丧失了 add() 方法的功能，编译器不通过。
     只保留与具体类型无关的功能,它只关心元素的数量、容器是否为空
     提高了代码的可读性，程序员看到这段代码时，就能够迅速对此建立极简洁的印象，能够快速推断源码作者的意图
     */
    private static void wildList(Collection<?> collection) {
        collection.isEmpty();
        collection.iterator();
        collection.size();
    }

    /*
     通配符<?> 能干的事情都可以用类型参数<T>替换,能够进行写操作 只不过要进行强制转换。
     */
    private static <T> void wildListT(Collection<T> collection) {
        collection.isEmpty();
        collection.iterator();
        collection.size();
        collection.add((T) new Integer(1));
    }

    /**
     * 被称作有上限的通配符  <? extends T> 代表类型 T 及 T 的子类。
     */
    public void collectionUp(Collection<? extends Base> collection) {
        //编译不通过
        /**
         collection.add(new Base());//编译不通过
         collection.add(new Sub());//编译不通过
         **/
    }


    /**
     * 被称作有下限的通配符 <? super T> 代表类型 T 及 T 的超类
     */
    public void collectionDown(Collection<? super Sub> para) {
        para.add(Sub.builder().value(1).num(1).build());
        /**
         para.add(new Base());//编译不通过
         **/
    }

}
