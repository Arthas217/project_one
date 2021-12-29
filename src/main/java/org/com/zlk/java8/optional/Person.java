package org.com.zlk.java8.optional;

import java.util.Optional;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/12/28 21:56
 */
public class Person {

    /**
     * 人可能有汽车，也可能没有汽车，因此将这个字段声明为 Optional
     */
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}
