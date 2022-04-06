package org.com.zlk.serializable;

import java.io.Serializable;

/**
 * @Author 会游泳的蚂蚁
 * @Description:  类中存在引用对象,这个类对象在什么情况下可以实现序列化
 * @Date 2022/4/3 15:17
 */
public class Combo implements Serializable {

    private int id;
    private People people;

    public Combo(int id, People people) {
        this.id = id;
        this.people = people;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "Combo{" +
                "id=" + id +
                ", people=" + people +
                '}';
    }
}
