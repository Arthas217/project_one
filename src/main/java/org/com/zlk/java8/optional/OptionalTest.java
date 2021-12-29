package org.com.zlk.java8.optional;

import java.util.Optional;
import java.util.function.Function;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/12/28 19:52
 */
public class OptionalTest {

    public static void main(String[] args) {
        Optional<String> str = Optional.empty();
        System.out.println(str);

        //这段代码就会立即抛出一个 NullPointerException，而不是等到你试图访问 str的属性值时才返回一个错误。
//        String str1 = null;
//        Optional<String> optCar = Optional.of(str1);
//        System.out.println(optCar);

        String str2 = null;
        Optional<String> optional = Optional.ofNullable(str2);
        System.out.println(optional);


        System.out.println("============");
        Insurance insurance1 = null;
        Optional<Insurance> optional1 = Optional.ofNullable(insurance1);
        optional1.ifPresent(insurance -> System.out.println(insurance.getName()));
        System.out.println(optional1);
        System.out.println("============");

        Insurance insurance = new Insurance();
        insurance.setName("测试optional");
        Optional<Insurance> insurance2 = Optional.ofNullable(insurance);
//        Optional<Insurance> insurance2 = Optional.ofNullable(insurance1);
        Function<Insurance, String> function = Insurance::getName;
        Optional<String> name = insurance2.map(function);
        System.out.println(name);

        System.out.println("-------------");
        Person person = new Person(); // 只new一个person的话   调用getCarInsuranceName会报异常，为什么？
        Car car = new Car();
        Insurance insurance3 = new Insurance();
        Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance3);
        car.setInsurance(optionalInsurance);
        Optional<Car> optionalCar = Optional.ofNullable(car);
        person.setCar(optionalCar);
        Optional<Person> optionalPerson = Optional.ofNullable(person);
        String carInsuranceName = getCarInsuranceName(optionalPerson);
        System.out.println(carInsuranceName);

    }


    /**
     * 使用Optional重新定义Person/Car/Insurance的数据模型
     *
     * @param person
     * @return
     */
    public static String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknow");
    }

}
