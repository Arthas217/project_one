package org.com.zlk.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 实体类对象赋值方式
 * new、构造、builder、链式编程方式、lombok的Builder注解
 * @Date 2022/7/1 12:40
 */
@Data
// 链式
@Accessors(chain = true)
public class Student {
    private int age;
    private int score;
}
