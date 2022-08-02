package org.com.zlk.log;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/7/27 15:53
 */
public class LogDemo {
    private static final Logger logger = LoggerFactory.getLogger("LogDemo");

    public static void main(String[] args) {
        if (logger.isDebugEnabled()) {
            System.out.println("=========");
        }
        System.out.println(JSONObject.toJSONString(MapUtils.EMPTY_MAP));
    }
}
