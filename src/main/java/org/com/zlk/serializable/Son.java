package org.com.zlk.serializable;

import java.io.Serializable;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/4/2 13:19
 */
public class Son extends People implements Serializable{

    private String name;
    private Integer age;

    public Son(Long id, String name, Integer age) {
        super(id);
        this.name = name;
        this.age = age;
    }

    /**
     * 不加此方法，则调用父类toString方法，People{id=null}
     */
    @Override
    public String toString() {
        return "Son{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
