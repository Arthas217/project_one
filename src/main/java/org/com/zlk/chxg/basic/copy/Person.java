package org.com.zlk.chxg.basic.copy;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/8/11 11:38
 */
public class Person {

    private String name;
    private Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
