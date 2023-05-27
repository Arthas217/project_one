package org.com.zlk.chxg.design.bridge;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 权益中心对客展示接口
 * @Date 2023/5/27 20:29
 */
public interface IRightsAndInterests {

    /**
     * 获取用户享有的权益及客户信息
     * @return
     */
    Rights queryRights();
}
