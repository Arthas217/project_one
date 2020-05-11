package org.com.zlk.annotation.lombook;

public class LomBookTest {

    public static void main(String[] args) {
        Man.ManBuilder zlk = Man.builder().id(2).age(31).name("Zlk");
        Man man = zlk.build();
        System.out.println(man.getId() + " " +man.getAge() + " " +man.getName());
    }
}
