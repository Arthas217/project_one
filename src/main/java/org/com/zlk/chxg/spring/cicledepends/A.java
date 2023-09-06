package org.com.zlk.chxg.spring.cicledepends;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/9/6 11:32
 */
public class A {

    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

}
