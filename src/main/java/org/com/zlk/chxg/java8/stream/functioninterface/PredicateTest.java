package org.com.zlk.chxg.java8.stream.functioninterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 内置Predicate函数式接口---它表示一个断言，接收一个参数并返回一个boolean值。
 * 此接口场景
 * 过滤集合：可以使用 Predicate 对集合中的元素进行过滤，只保留符合特定条件的元素；
 * 验证输入：可以使用 Predicate 对输入数据进行验证，判断其是否满足特定要求；
 * 判断状态：可以使用 Predicate 对对象的某些状态进行判断，从而决定下一步操作如何执行等。
 *
 * Predicate是一个通用的谓词接口，它可以应用于任何对象类型。
 * IntPredicate是一个用于int值的谓词，只能用于int值而Predicate
 * @Date 2021/12/27 22:18
 */
public class PredicateTest {

    public static void main(String[] args) {
        //test方法，如果传入的对象符合 Predicate 所描述的条件，则返回 true；否则返回 false。
        predicate_test();
        //and默认方法，将当前Predicate对象与另一个Predicate对象进行逻辑“与”操作，返回一个新的Predicate对象。
        predicate_and();
        //or默认方法，将当前Predicate对象与另一个Predicate对象进行逻辑“或”操作，返回一个新的Predicate对象。
        predicate_or();
        //negate默认方法，返回一个新的Predicate对象，该对象表示原始Predicate对象的逆。对于原始对象返回true的条件，返回的新对象返回false，反之亦然。
        predicate_negate();
        //isEqual默认方法,允许我们创建一个通用的断言函数Predicate，该函数可以测试输入对象是否与指定对象相等
        predicate_isEqual();
        //该方法接受一个int值作为参数并返回一个布尔值，用于检查一个整数是否符合某些条件。
        int_predicate();

    }

    private static void int_predicate() {
        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        System.out.println(evenNumbers.test(2));
    }

    private static void predicate_isEqual() {
        List<String> list = Arrays.asList("1","2","3");
        String target = "21";
        //创建一个Predicate实例，与target相等的元素：
        Predicate<String> predicate = Predicate.isEqual(target);
        //返回list中与target相等的第一个元素，如果不存在这样的元素，则返回null。
        String result = list.stream().filter(predicate).findFirst().orElse(null);
        System.out.println(result);
    }

    private static void predicate_negate() {
        Predicate<String> p = s -> s.equals(" ");
        boolean isSpace = p.test(" "); // true
        boolean isNotSpace = p.negate().test(" "); // false
        System.out.println("isSpace:" + isSpace + "  isNotSpace:" + isNotSpace);
    }

    private static void predicate_or() {
        Predicate<Integer> predicate1 = num -> num < 0;
        Predicate<Integer> predicate2 = num -> num % 2 == 0;
        // predicate3表示小于零的奇数、偶数（正数、负数）
        Predicate<Integer> predicate3 = predicate1.or(predicate2);

        System.out.println(predicate1.test(-1)); // true
        System.out.println(predicate1.test(1)); // false
        System.out.println(predicate2.test(2)); // true
        System.out.println(predicate2.test(3)); // false

        System.out.println(predicate3.test(-1)); // true
        System.out.println(predicate3.test(2)); // true
        System.out.println(predicate3.test(3)); // false
        System.out.println(predicate3.test(-2)); // true
    }

    private static void predicate_and() {
        Predicate<Integer> predicate1 = num -> num > 0;
        Predicate<Integer> predicate2 = num -> num % 2 == 0;
        // predicate3表示大于零的偶数
        Predicate<Integer> predicate3 = predicate1.and(predicate2);
        System.out.println(predicate3.test(2));
    }

    private static void predicate_test() {
        // 第二个参数可以使用Lambda表达式，它是一种匿名函数，可以在需要函数的地方直接定义函数而不必定义命名函数，简化代码。
        List<String> result = filter(Arrays.asList("ab", "abcde", "bcd"), (String str) -> str.length() > 4);
        System.out.println(result);
    }


    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                //只保留长度大于2的元素
                result.add(t);
            }
        }
        return result;
    }


}
