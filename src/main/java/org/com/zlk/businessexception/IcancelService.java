package org.com.zlk.businessexception;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 取消服务 https://mp.weixin.qq.com/s/PGGOR263SZV9ILCDMpT9OA
 * @Date 2022/7/2 09:51
 */
public interface IcancelService {

    /**
     * 取消服务
     * @param serviceOrderId
     * @return
     */
    ApiResult cancelService(Long serviceOrderId);


    /**
     * 取消服务单(不用断言)
     * @param serviceOrderId
     * @return
     */
    ApiResult cancelService2(Long serviceOrderId);

}
