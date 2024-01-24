package org.com.zlk.genericstype.jiagoushi;

/**
 * https://mp.weixin.qq.com/s/8fjgKRXGv39n5xaH_JeO2Q
 *
 * @author 会游泳的蚂蚁
 * @date 2024/1/24 13:04
 */
public class PlateDemo {

    public static void main(String[] args) {

        // 错误1  苹果 is a 水果, 但是装苹果的盘子 not is 装水果的盘子（容器之间没有继承关系）
//        Plate<Fruit> fruitPlate = new Plate<Apple>();

        // 使用泛型解决（上界）<？extends 基类B> 上界是“基类B”
        // 可能是“基类B” 或者是“基类B”的子类；
        Plate<? extends Fruit> fruitPlate = new Plate<Apple>();



        // 错误2 往基类盘子不能放入任何元素
//        fruitPlate.setSomething(new 黑龙江Apple());
//        fruitPlate.setSomething(new 云南Apple());
//        fruitPlate.setSomething(new Apple());

        // 可以往外取值
        System.out.println(fruitPlate.getSomething());



        //使用泛型下界  <？super 子类C>  表示泛型实参类型的下界是“子类C" 或者是“子类C”的基类；
        Plate<? super Fruit> fruitPlate2 = new Plate<Food>();
        //设置任何子类对象
        fruitPlate2.setSomething(new Apple());
        fruitPlate2.setSomething(new 黑龙江Apple());
        fruitPlate2.setSomething(new 云南Apple());
        fruitPlate2.setSomething(new Banana());
        fruitPlate2.setSomething(new Fruit());
//        fruitPlate2.setSomething(new Food());
//        fruitPlate2.setSomething(new Object());

        // 错误3 不能往外取值，除非是Object对象
//        Fruit fruit = fruitPlate2.getSomething();
        Object object = fruitPlate2.getSomething();


    }

}
