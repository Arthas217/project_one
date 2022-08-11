package org.com.zlk.zdjx;

import com.alibaba.fastjson.JSONObject;
import org.com.zlk.zhouyang.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://www.cnblogs.com/lingyejun/p/9366533.html
 * @Date 2022/3/23 17:52
 */
public class LogTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        try{
//            printInfo();

            // 模拟空指针异常
            Integer nullInt = Integer.valueOf(null);
            // 数组越界
//            int[] array = {1,2,3,4,5};
//            int outBoundInt = array[5];
        }catch (Exception e){
            LOGGER.info("=======info=========={}",e.getMessage());
            // 使用字符串拼接
            LOGGER.error("使用 + 号连接直接输出 e ===== " + e);
            LOGGER.error("使用 + 号连接直接输出 e.getMessage()====== : " + e.getMessage());
            LOGGER.error("使用 + 号连接直接输出 e.toString()===== : " + e.toString());
//            // 使用逗号分隔，调用两个参数的error方法
            LOGGER.error("使用,号 第二个参数为Throwable =====", e);
//            // 尝试使用分隔符,第二个参数为Throwable,会发现分隔符没有起作用，第二个参数的不同据，调用不同的重载方法
            LOGGER.error("使用,号 第二个参数为Throwable 使用分隔符打印 e {} : ", e);
//            // 第二个参数为Object
            LOGGER.error("第二个参数为Object，使用分隔符打印 e.getMessage() ======{} : ", e.getMessage());
//            // 尝试使用分隔符，第二个参数为Object,会发现分隔符起作用了，根据第二个参数的不同类型，调用不同的重载方法
            LOGGER.error("第二个参数为Object，使用分隔符打印 123 ====={} ",123);
        }
    }

    private static void printInfo() {
        List<User> list = new ArrayList<>();
        User user1 = new User("1",1);
        User user2 = new User("2",2);
        list.add(user1);
        list.add(user2);
        Map<String,Object> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        Map<String,Object> map1 = new HashMap<>();
        map1.put("3","3");
        map1.put("4","4");
        List<Map<String,Object>> list1 = new ArrayList<>();
        list1.add(map);
        list1.add(map1);
        LOGGER.info("111");
        LOGGER.info("=======info=========={}",list);
        LOGGER.info("=======info=========={}",list.toString());
        LOGGER.info("=======info=========={}", JSONObject.toJSONString(list));
        LOGGER.info("=======info=========={}", map);
        LOGGER.info("=======info=========={}", JSONObject.toJSONString(map));
        LOGGER.info("=======info=========={}", JSONObject.toJSONString(list1));
        System.out.println("01".substring(1,2));
        System.out.println("null".substring(0,1));
    }
}
