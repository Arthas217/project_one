package org.com.zlk.java8.zzzz.one;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/8/4 11:38
 */
public class test_01 {

    public static void main(String[] args) {
        IFormula formula = a -> a * a * a; // 参数和实现类
        System.out.println(formula.calculate(2));
        System.out.println(formula.sqrt(1));


        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, Comparator.reverseOrder());
    }
}
