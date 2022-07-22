package org.com.zlk.kengkeng;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://mp.weixin.qq.com/s/EHNlUIjEhTgp2Q7JT9pscQ
 * @Date 2022/7/22 15:27
 */
public class IntegerDemo {

    public static void main(String[] args) {
        // 1. 用==号比较的坑(有些状态字段，比如：orderStatus有：-1(未下单)，0（已下单），1（已支付），2（已完成），3（取消）)
        method1();
        // 2. Objects.equals的坑
        method2();
    }

    private static void method2() {

    }

    private static void method1() {
        Integer orderStatus1 = new Integer(1);
        Integer orderStatus2 = new Integer(1);
        // false
        System.out.println(orderStatus1 == orderStatus2);
        // Integer中不是有范围是：-128-127的缓存?  答案在valueOf方法
        System.out.println(Integer.valueOf(orderStatus1) == Integer.valueOf(orderStatus2));
        // 尽量少用==判断两个Integer类型数据是否相等，只有在上述非常特殊的场景下才相等。  而应该改成使用equals方法判断：
        System.out.println(orderStatus1.equals(orderStatus2));
    }
}
