package org.com.zlk;

import org.com.zlk.model.US;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        US.USBuilder zlk = US.builder().id(2).age(31).name("zlk");
        US us = zlk.build();
        System.out.println(us.getId() + " " +us.getAge());
    }
}
