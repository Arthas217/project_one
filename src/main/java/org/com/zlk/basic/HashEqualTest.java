package org.com.zlk.basic;

import java.util.HashMap;

public class HashEqualTest {

    public static void main(String[] args) {

        Key k1 = new Key(1);
        Key k2 = new Key(1);

        HashMap<Key, String> hashMap = new HashMap();
        System.out.println("当我们往HashMap里放 k1时，首先会调用 Key这个类的 hashCode方法计算它的 hash值，随后把 k1放入hash值所指引的内存位置。");
        hashMap.put(k1, "put k1 success");

        System.out.println("k1 hashcode值：" + k1.hashCode());
        System.out.println("k1 value:" + hashMap.get(k1));

        System.out.println("没有重写hashCode方法");
        System.out.println("调用Object类的 hashCode方法（所有的类都是Object的子类），而 Object类的 hashCode方法返回的 hash值其实是 k1对象的 内存地址。");
        System.out.println("由于 k1和 k2是两个不同的对象，所以它们的内存地址一定不会相同，也就是说它们的 hash值一定不同");
        System.out.println("k2 hashcode值：" + k2.hashCode());

        System.out.println("没有重写 Key对象的 equals方法  Object的固有方法是根据两个对象的内存地址来判断，所以 k1和 k2一定不会相等");
        System.out.println("hashCode方法返回的hash值相同的情况下，链地址法来处理冲突, equals方法来判断两者是否相等");
        System.out.println("k2 value:" + hashMap.get(k2));




    }
}
