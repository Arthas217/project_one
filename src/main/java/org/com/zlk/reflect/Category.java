package org.com.zlk.reflect;

import java.util.List;

/**
 * @Author zc217
 * @Date 2021/1/4
 */
public interface Category {

    /*添加分类*/
    @Permission("添加分类")
    void addCategory(Category category);

    /*查找分类*/
    void findCategory(String id);

    /*查看分类*/
    @Permission("查找分类")
    List<Category> getAllCategory();
}
