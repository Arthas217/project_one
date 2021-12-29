package org.com.zlk.java8.optional;

import java.util.Optional;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/12/28 21:55
 */
public class Car {
    /**
     * 汽车可能进行了保险，也可能没有保险，
     * 所以将这个字段声明为Optional
     */
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
}
