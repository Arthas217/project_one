package org.com.zlk.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/1/19 18:38
 */
public class DemoBeanUtils {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        UserInfo userInfo = new UserInfo();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("userId", "123");
        properties.put("userName", "中国");
        BeanUtils.populate(userInfo, properties);
        System.out.println(userInfo.getUserId());
        System.out.println(userInfo.getUserName());
    }
}
