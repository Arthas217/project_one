package org.com.zlk.chxg.spring.cicledepends;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/9/6 11:32
 */
public class B {

    private A a;


    public void setA(A a) {
        this.a = a;
    }

    public A getA() {
        return a;
    }
}
