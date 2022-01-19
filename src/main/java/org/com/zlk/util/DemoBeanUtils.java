package org.com.zlk.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 参考https://blog.csdn.net/jianggujin/article/details/51104949
 * @Date 2022/1/19 18:38
 */
public class DemoBeanUtils {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        UserInfo userInfo = new UserInfo();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("userId", "123");
        properties.put("userName", "中国");
        BeanUtils.populate(userInfo, properties);
        System.out.println(userInfo.getUserId());
        System.out.println(userInfo.getUserName());

        Method method = MethodUtils.getAccessibleMethod(UserInfo.class, "setUserName", String.class);
        method.invoke(userInfo,"哈尔滨");
        System.out.println(userInfo.getUserName());

        MethodUtils.invokeMethod(userInfo, "setUserName", "阿城区");
        System.out.println(userInfo.getUserName());

    }
}
