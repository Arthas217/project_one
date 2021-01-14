package org.com.zlk.genericstype.jj;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 泛型通配符？和T区别
 * https://mp.weixin.qq.com/s/4rdjwXmxMzjkxtR8krGoVQ
 * @Date 2021/1/14 11:14
 */
public class Test<T> implements MultiLimitInterfaceA, MultiLimitInterfaceB {


    //区别1 通过T来确保泛型参数的一致性
    public static <T extends Number> void test(List<T> dest, List<T> src) {
        System.out.println(dest);
        System.out.println(src);
    }

    //通配符是不确定的，所以这个方法不能保证两个List具有相同的元素类型
    public static void test2(List<? extends Number> dest, List<? extends Number> src) {
        System.out.println(dest);
        System.out.println(src);
    }


    // 区别2：参数可以多重限定 通配符T行而?不行
    // 使用 & 符号设定多重边界（Multi Bounds)
    // 指定泛型类型 T 必须是 MultiLimitInterfaceA 和 MultiLimitInterfaceB 的共有子类型
    // 此时变量 t 就具有了所有限定的方法和属性
    public static <T extends MultiLimitInterfaceA & MultiLimitInterfaceB> void test3(T t) {
        System.out.println(t);
    }

    // 区别3：通配符?可以使用超类限定而类型参数T不行
    // T extends A
    // ? extends A
    // ? super A


    // Class<T>在实例化的时候，T要替换成具体类。
    public static <T> T createInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    // Class<?>主要用于声明时的限制情况
    public Class<?> clazz;
    // 不可以，因为T需要指定类型, 解决的话 必须让当前的类也指定 T
    public Class<T> clazzT;

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        List<AtomicInteger> dest = new ArrayList<>();
        List<AtomicInteger> src = new ArrayList<>();
        List<AtomicLong> src2 = new ArrayList<>();
        List<String> src3 = new ArrayList<>();
        Test.test(dest, src);
        Test.test2(dest, src2);

        // Class<T>和 Class<?>区别
        // 通过反射的方式生成，需要使用强制类型转换
        // 在运行期，如果反射的类型不是Test 类，那么一定会报 java.lang.ClassCastException 错误。
        try {
            Test t1 = (Test) Class.forName("org.com.zlk.genericstype.jj.Test").newInstance();
            // 对于这种情况,使用createInstance，在编译期就能直接检查到类型的问题
            Test t2 = createInstance(Test.class);
        } catch (Exception e) {
        }
    }

}
