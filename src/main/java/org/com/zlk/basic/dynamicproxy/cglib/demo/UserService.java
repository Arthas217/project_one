package org.com.zlk.basic.dynamicproxy.cglib.demo;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/11/11 10:24
 */
public class UserService implements IUserService{
    @Override
    public String queryUserNameById(String userId) {
        return "hi user " + userId;
    }
}
