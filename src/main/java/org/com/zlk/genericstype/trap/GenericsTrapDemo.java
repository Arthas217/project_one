package org.com.zlk.genericstype.trap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description:泛型注意陷阱 https://www.cnblogs.com/hongdada/p/10683795.html
 * @Date 2022/8/25 16:58
 */
public class GenericsTrapDemo {


    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    /**
     * <? extends T> a
     * a 这个变量可以接受 T 及其 T 子类的集合，上界为 T，并且从 a 取出来的类型都会被强制转换为 T
     */
    private static void test3() {
        List<Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<RedCat> redCats = new ArrayList<>();

        List<? extends Cat> extendsCat1 = cats;
        List<? extends Cat> extendsCat2 = redCats;
        // 不能通过编译，因为只能接受 Cat 及其子类的集合
//        List<? extends Cat> extendsCat3 = animals;

        // 重点注意下面三行都不能通过编译
        // 不能向里面添加除null之外的其他所有元素，这个和 List<?> 有点类似
//        extendsCat1.add(new Cat());
//        extendsCat2.add(new RedCat());
//        extendsCat3.add(new Animal());
        // 重点注意：可以通过编译，
        extendsCat1.add(null);
    }

    /**
     * <? super T> a
     * a 这个变量可以接受 T 及其 T 父类的集合，下界为 T，并且从 a 取出来的类型都会被强制转换为 Object
     * 最需要注意的是，在虽然可以接受 T 及其父类的赋值，但是只能向里面添加 T 及其 T 的子类。
     */
    private static void test4() {
        List<Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<RedCat> redCats = new ArrayList<>();

        // 可以通过编译
        List<? super Cat> superCat1 = animals;
        List<? super Cat> superCat2 = cats;
        // 不能通过编译，因为只能接受 Cat 及其父类的集合
//        List<? super Cat> superCat3 = redCats;

        // 重点注意：不能通过编译,只能添加 Cat 及其 Cat 的子类
//        superCat1.add(new Animal());
        // 重点注意，可以通过编译
        superCat2.add(new Cat());
//        superCat3.add(new RedCat());
        superCat1.add(null);
    }

    /**
     * List和List<Object>区别
     */
    private static void test1() {
        List<Integer> t1 = new ArrayList<>();
        List t2 = t1;
        System.out.println(t1 == t2);
        // 编译报错 List<Object> t3 = t1;
    }

    /**
     * List<?>注意点
     * List<?> 是一个泛型，在没有赋值之前，是可以接受任何集合的赋值的，但是请注意，赋值之后就不能往里面添加元素了
     * 所以 List<?> 一般用来作为参数来接受外部的集合，或者返回一个不知道具体元素的集合。
     */
    private static void test2() {
        List<Object> t1 = new ArrayList<>();
        t1.add("1");
        t1.add("2");
        List<?> t2 = t1;
        // 编译通过
        t2.remove(0);
        // 编译不通过  t2.add(new Object());
        System.out.println(t1);
        System.out.println(t2);
        t2.clear();
        System.out.println(t1);
        System.out.println(t2);
    }


    /**
     * List泛型与重载
     */
    private static void test5() {
        List<Integer> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        //泛型只存在于源码中，在编译后的字节码中，泛型已经被替换为原生类型了，并且在相应的地方插入了强制转换的代码。
        method(list1,1);
        method(list2);
    }

    /**
     * javap -p -s
     * https://www.zhihu.com/question/38940308
     * descriptor: (Ljava/util/List;I)V
     * @param list
     * @param n
     */
    public static void  method(List<Integer> list,int n) {
        System.out.println("List<Integer> list,int n");
    }

    /**
     * descriptor: (Ljava/util/List;)V
     * @param list
     */
    public static void method(List<String> list) {
        System.out.println("List<String> list");
    }

    /**
     * 数组转换集合
     */
    private static void test6() {
        String[] arr = {"one", "two", "three"};
        // 数组转换成集合
        List<String> list = Arrays.asList(arr);
        // 向集合添加元素：编译正常，但运行时抛出了异常
        // 返回的List内部直接引用了原数组arr，原数组长度固定为3，所以不可以再add
//        list.add("four");

        //建议大家这样转换比较安全
        List<String> safeList = new ArrayList<>(Arrays.asList(arr));
        safeList.add("four");
        System.out.println(safeList);

    }


}
