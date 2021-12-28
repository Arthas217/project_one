package org.com.zlk.java8.stream;

import java.util.function.IntPredicate;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/12/28 09:48
 */
public class IntPredicateTest {

    /**
     * 对应参数非包装类型的predicate、 其他例子：对应返回为基本类型的ToIntFunction
     */
    private  IntPredicate evenNumbers = (int i) -> i % 2 == 0;

    public void intPredicateTest(){
        System.out.println(evenNumbers.test(2));
    }

    public static void main(String[] args) {
        IntPredicateTest intPredicateTest = new IntPredicateTest();
        intPredicateTest.intPredicateTest();
    }
}
