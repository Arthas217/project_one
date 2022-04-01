package org.com.zlk.zdjx;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/3/18 12:19
 */
public class NameTest {


    /**
     * 前后几位数字不被替换
     */
    public static void method1(){
        String name = "23030219880403441X";
        String regex = "(\\w{3})(\\w+)(\\w{2})";
        name = name.replaceAll(regex, "$1****$3");
        System.out.println(name);
        String news = name.substring(0,name.length()-1);
        String substring = news.replace("[\\u4e00-\\u9fa5]","*") + news.substring(name.length() - 1);
        System.out.println(substring);
    }

    public static void main(String[] args) {


    }
}
