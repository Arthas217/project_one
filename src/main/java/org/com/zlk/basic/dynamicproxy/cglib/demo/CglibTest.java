package org.com.zlk.basic.dynamicproxy.cglib.demo;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://bugstack.cn/md/java/core/2019-12-21-%5B%E6%9C%89%E7%82%B9%E5%B9%B2%E8%B4%A7%5DJDK%E3%80%81CGLIB%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86%E4%BD%BF%E7%94%A8%E4%BB%A5%E5%8F%8A%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90.html
 * @Date 2022/11/11 10:25
 */
public class CglibTest {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();//481
        // 代理对象userService
        UserService userService = (UserService) cglibProxy.newInstance(new UserService());//返回aa95a  485
        String userName = userService.queryUserNameById("10001");
        System.out.println(userName);
    }
}
