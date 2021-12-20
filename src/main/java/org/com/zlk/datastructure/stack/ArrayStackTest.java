package org.com.zlk.datastructure.stack;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/12/20 18:08
 */
public class ArrayStackTest {

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(3);
        arrayStack.push("1");
        arrayStack.push("2");
        System.out.println(arrayStack.pop());
        arrayStack.push("3");
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
    }
}
