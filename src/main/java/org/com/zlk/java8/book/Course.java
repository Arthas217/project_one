package org.com.zlk.java8.book;

import com.google.common.base.Enums;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/22 10:41
 */
public enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private Food[] values;

    Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food randomSelection() {
        return EnumUtil.random(values);
    }
}
