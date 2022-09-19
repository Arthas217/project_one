package org.com.zlk.businessexception;

import cn.hutool.core.util.ObjectUtil;
import org.com.zlk.businessexception.v3.AssertUtil;
import org.com.zlk.enum1.ErrorCodeEnum;
import org.com.zlk.vo.Student;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 接口处理业务逻辑
 * @Date 2022/7/2 09:53
 */
public class CancelServiceImpl implements IcancelService {

    /**
     * v1.0
     *
     * @param serviceOrderId
     * @return
     */
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

    /**
     * v1.1
     *
     * @param serviceOrderId
     * @return
     */
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


    /**
     * v1.3
     * 异常处理类+断言
     * @param serviceOrderId
     * @return
     */
    public ApiResult cancelService3(@PathVariable Long serviceOrderId) {
        Student student = new Student().setScore(10);
        AssertUtil.businessInvalid(ObjectUtil.isNull(student), "查无此服务单");
        AssertUtil.businessInvalid("1".equals("0"), "查无此服务单");
        return ApiResult.success();
    }
}
