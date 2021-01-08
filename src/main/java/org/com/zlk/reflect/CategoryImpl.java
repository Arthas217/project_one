package org.com.zlk.reflect;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zc217
 * @Date 2021/1/4
 */
public class CategoryImpl implements Category {
    @Override
    public void addCategory(Category category) {
        System.out.println("addCategory");
    }

    @Override
    public void findCategory(String id) {
        System.out.println(id + " findCategory");
    }

    @Override
    public List<Category> getAllCategory() {
        return new ArrayList<>();
    }
}
