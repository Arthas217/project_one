package org.com.zlk.serializable;

import java.io.Serializable;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 父类
 * @Date 2022/4/2 13:04
 */
//public class People {// 测试二使用
public class People implements Serializable{ // 修复测试一,修复测试三,测试四

    private Long id;

    // 测试二，不加无参构造方法会报错java.io.InvalidClassException: org.com.zlk.serializable.Son; no valid constructor
    // 测试四，注释掉
    public People() {
        //id= 1l;//赋值
    }

    public People(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 不加此方法，打印的是org.com.zlk.serializable.People@28c97a5
     * 添加此方法，打印的是People{id=10}
     */
    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                '}';
    }
}
