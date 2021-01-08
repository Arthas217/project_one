package org.com.zlk.reflect;

import java.util.List;

/**
 * @Author zc217
 * @Date 2021/1/4
 */
public class Test {

    public static void main(String[] args) {
        ServiceDaoFactory instance = ServiceDaoFactory.getInstance();
        User user = new User();
        user.setId(1);
        Category category = instance.createDao("org.com.zlk.reflect.CategoryImpl",Category.class, user);
        System.out.println(category);
        List<Category> allCategory = category.getAllCategory();
        System.out.println(allCategory);
    }
}
