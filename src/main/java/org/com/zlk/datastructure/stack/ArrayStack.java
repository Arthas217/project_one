package org.com.zlk.datastructure.stack;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 基于数组实现顺序栈
 * @Date 2021/12/20 18:00
 */
public class ArrayStack {

    /**
     * 存储栈元素
     */
    private String[] items;
    /**
     * 栈中元素个数
     */
    private int count;
    /**
     * 栈的大小
     */
    private int size;

    /**
     * 初始化大小为n的栈
     * @param n
     */
    public ArrayStack(int n) {
        this.items = new String[n];
        this.count = 0;
        this.size = n;
    }

    /**
     * 入栈操作
     * @param item
     * @return
     */
    public boolean push(String item) {
        // 栈空间慢，入栈失败
        if (count == size) {
            return false;
        }
        items[count] = item;
        ++count;
        return true;
    }

    // 出栈操作
    public String pop() {
        if (count == 0) {
            return null;
        }
        String value = items[count - 1];
        --count;
        return value;
    }
}
