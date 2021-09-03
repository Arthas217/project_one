package org.com.zlk.java8.zzzz;

import org.com.zlk.java8.zzzz.two.Person;

import java.util.Optional;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/8/4 12:59
 */
public class OptionalTest {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("china");
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
        System.out.println(optional.orElse("beijing"));

        optional.ifPresent(s -> System.out.println(s.charAt(4)));
        Optional<Person> optionalPerson = Optional.of(new Person());
        System.out.println(optionalPerson);
        optionalPerson.ifPresent(s -> System.out.println(s.firstName));




    }
}
