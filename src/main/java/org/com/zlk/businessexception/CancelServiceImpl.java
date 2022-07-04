package org.com.zlk.businessexception;

import cn.hutool.core.util.ObjectUtil;
import org.com.zlk.vo.Student;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/7/2 09:53
 */
public class CancelServiceImpl implements IcancelService{

    @Override
    public ApiResult<Student> cancelService(Long serviceOrderId) {
        //模拟mapper得到DAO一个对象
        Student student = new Student().setScore(10);
        ApiResult<Student> apiResult = new ApiResult();
        if (ObjectUtil.isNull(student)) {
            apiResult.setSuccess(false);
            apiResult.setResponseCode(ErrorCodeEnum.FAIL.getCode());
            apiResult.setResponseMsg("查无此服务单");
            return apiResult;
        }
        return apiResult;
    }

    @Override
    public ApiResult cancelService2(Long serviceOrderId) {
        //模拟mapper得到DAO一个对象
        Student student = new Student().setScore(10);
        ApiResult<Student> apiResult = new ApiResult();
        if (ObjectUtil.isNull(student)) {
            apiResult = ApiResult.fail(ErrorCodeEnum.FAIL.getCode(), "查无此服务单", null);
            return apiResult;
        }
        return apiResult;
    }


}
