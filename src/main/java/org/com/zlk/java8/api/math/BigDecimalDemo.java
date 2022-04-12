package org.com.zlk.java8.api.math;

import java.math.BigDecimal;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://mp.weixin.qq.com/s/2qwJun8t_-b849kU9Dvi7A
 * @Date 2022/4/12 12:26
 */
public class BigDecimalDemo {

    public static void main(String[] args) {
        //构造函数
        BigDecimal b1 = new BigDecimal(1);
        BigDecimal b2 = new BigDecimal(2D);
        BigDecimal b3 = new BigDecimal(3L);
        BigDecimal b4 = new BigDecimal("2");
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b4);


    }
}
