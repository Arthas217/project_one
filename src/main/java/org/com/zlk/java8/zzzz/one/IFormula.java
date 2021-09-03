package org.com.zlk.java8.zzzz.one;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/8/4 11:38
 */
public interface IFormula {

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
