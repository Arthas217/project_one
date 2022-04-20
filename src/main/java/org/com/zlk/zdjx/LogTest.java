package org.com.zlk.zdjx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://www.cnblogs.com/lingyejun/p/9366533.html
 * @Date 2022/3/23 17:52
 */
public class LogTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        try{
            // 模拟空指针异常
            //Integer nullInt = Integer.valueOf(null);
            int[] array = {1,2,3,4,5};
            int outBoundInt = array[5];
        }catch (Exception e){
            // 使用字符串拼接
            LOGGER.error("使用 + 号连接直接输出 e : " + e);
            LOGGER.error("使用 + 号连接直接输出 e.getMessage() : " + e.getMessage());
            LOGGER.error("使用 + 号连接直接输出 e.toString() : " + e.toString());
            // 使用逗号分隔，调用两个参数的error方法
            LOGGER.error("使用 , 号 使第二个参数作为Throwable : ", e);
            // 尝试使用分隔符,第二个参数为Throwable,会发现分隔符没有起作用，第二个参数的不同据，调用不同的重载方法
            LOGGER.error("第二个参数为Throwable，使用分隔符打印 e {} : ", e);
            // 第二个参数为Object
            LOGGER.error("第二个参数为Throwable，使用分隔符打印 e.getMessage(){} : ", e.getMessage());
            // 尝试使用分隔符，第二个参数为Object,会发现分隔符起作用了，根据第二个参数的不同类型，调用不同的重载方法
            LOGGER.error("第二个参数为Object，使用分隔符打印 {} ",123);
        }
    }
}
