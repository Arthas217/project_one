package org.com.zlk.basic.dynamicproxy.cglib.demo2;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/11/13 08:58
 */
public class UserManagerImpl implements UserManager{
    @Override
    public void addUser(String id, String userName) {
        System.out.println("调用了UserManagerImpl.addUser()方法！"+id+"-"+userName);
    }
}
