package org.com.zlk.kengkeng;

import org.com.zlk.zhouyang.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://mp.weixin.qq.com/s/EHNlUIjEhTgp2Q7JT9pscQ
 * @Date 2022/7/22 15:27
 */
public class FrequentKeng {

    public static void main(String[] args) {
        // 1. 用==号比较的坑(有些状态字段，比如：orderStatus有：-1(未下单)，0（已下单），1（已支付），2（已完成），3（取消）)
        method1();
        // 2. Objects.equals的坑
        method2();
        // 3. BigDecimal的坑
        method3();
        // 4. Java8 filter的坑
        method4();
        // 5. 自动拆箱的坑
//        method5();
        // 6. replace的坑
        method6();
    }

    private static void method6() {
        // 字符串比如：ATYSDFA*Y中的字符A替换成字符B
        // replace和replaceAll都能替换所有匹配字符，那么他们有啥区别呢？
        //1、replace有两个重载的方法。
        String source ="ATYSDFA*Y";
        System.out.println(source.replace('A', 'B'));
        System.out.println(source.replace("A", "B"));
        //2、replaceAll  对普通字符串进行替换：
        System.out.println(source.replaceAll("A", "B"));
        // replaceAll 使用正则表达替换（将*替换成C）：
        System.out.println(source.replaceAll("\\*", "C"));
        // 如果我只想替换第一个匹配的字符串该怎么办?
        System.out.println(source.replaceFirst("A", "B"));
    }

    private static void method5() {
        //自动装箱
        Integer in = 1;//等价于Integer in = new Integer(1);
        //自动拆箱
        Integer integer = new Integer(2);
        int sum = integer + 5;
        //等价于
        Integer inte = new Integer(2);
        int su = inte.intValue() + 5;
        // 实际工作中，我们在使用自动拆箱时，往往忘记了判空，导致出现NullPointerException异常。
        // 情况1、很多时候，我们需要对传入的数据进行计算
        System.out.println(add(null, new Integer(2)));
        // 情况2、有时候，我们定义的某个方法是基本类型，但实际上传入了包装类
        Integer a = new Integer(1);
        Integer b = null;
        System.out.println(add2(a, b));
        // 结论：Integer类型的参数，其实际传入值为null，JDK字段拆箱，调用了它的intValue方法导致的问题。

    }

    private static Integer add(Integer a, Integer b) {
        //自动拆箱时注意Integer的方法intValue
        return a + b;
    }
    private static Integer add2(int a, int b) {
        return a + b;
    }

    private static void method4() {
        // 如果你对过滤后的数据，做修改了：实际上，你会把元素数据一同修改了.其根本原因是：过滤后的集合中，保存的是对象的引用，该引用只有一份数据。
        // 伪代码
        List<User> userList = new ArrayList<>();
        List<User> filterList = new ArrayList<>(userList);
        for(User user: filterList) {
            user.setName(user.getName() + "测试");
        }
    }

    private static void method3() {
        // Double类型的两个参数相减会转换成二进制，因为Double有效位数为16位这就会出现存储小数位数不够的情况，这种情况下就会出现误差。
        double amount1 = 0.02;
        double amount2 = 0.03;
        System.out.println(amount2 - amount1);

        //BigDecimal能避免丢失精度吗？否定  此构造函数的结果可能不可预测，由此可见，使用BigDecimal构造函数初始化对象，也会丢失精度。
        BigDecimal b1 = new BigDecimal(0.02);
        BigDecimal b2 = new BigDecimal(0.03);
        System.out.println(b2.subtract(b1));

        //不丢失精度,可以使用Double.toString方法，对double类型的小数进行转换，这样能保证精度不丢失。
        BigDecimal b11 = new BigDecimal(Double.toString(0.02));
        BigDecimal b22 = new BigDecimal(Double.toString(0.03));
        System.out.println(b22.subtract(b11));

        //还有更好的办法：valueOf方法初始化BigDecimal类型参数，也能保证精度不丢失。在新版的阿里巴巴开发手册中，也推荐使用这种方式创建BigDecimal参数。
        BigDecimal bl1 = BigDecimal.valueOf(0.02);
        BigDecimal bl2 = BigDecimal.valueOf(0.03);
        System.out.println(bl2.subtract(bl1));
    }

    private static void method2() {
        //判断当前登录的用户为系统管理员，标识他用户age=25
        User user = new User("ONE",25);
        if(Objects.isNull(user)){
            System.out.println("----重新登录");
        }
        //这里age定义成int和Integer时，if判断都是false，  这就要从Integer的equals方法
        if(Objects.equals(user.getAge(),25L)) {
            System.out.println("-----------管理员-------");
        }
        System.out.println("++++++++++");
        //如果调用了Integer的equals方法，必须要求入参也是Integer类型，否则该方法会直接返回false。
        //除此之外，还有Byte、Short、Double、Float、Boolean和Character也有类似的equals方法判断逻辑。

        //Long类型和Integer类型比较，比如：用户id的场景。
        //Byte类型和Integer类型比较，比如：状态判断的场景。
        //Double类型和Integer类型比较，比如：金额为0的判断场景。
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
