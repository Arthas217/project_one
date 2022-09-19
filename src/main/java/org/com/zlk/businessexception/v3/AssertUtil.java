package org.com.zlk.businessexception.v3;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 增加断言工具类
 * @Date 2022/7/4 12:26
 */
public class AssertUtil {

    public AssertUtil() {
    }

    /**
     * 服务调用异常
     *
     * @param expression
     * @param message
     */
    public static void isTrueServiceInvoke(boolean expression, String message) {
        if (!expression) {
            throw new ServiceInvokeException(message);
        }
    }

    /**
     * 抛出异常(默认错误1000)
     *
     * @param message
     */
    public static void businessInvalid(String message) {
        throw new BusinessException(ApiCode.SERVICE_ERROR.getValue(), message);
    }

    /**
     * 表达式为真即抛出异常(默认错误1000)
     *
     * @param expression
     * @param message
     */
    public static void businessInvalid(boolean expression, String message) {
        if (expression) {
            throw new BusinessException(ApiCode.SERVICE_ERROR.getValue(), message);
        }
    }

    /**
     * 表达式为真即抛出异常
     *
     * @param expression
     * @param message
     */
    public static void businessInvalid(boolean expression, int code, String message) {
        if (expression) {
            throw new BusinessException(code, message);
        }
    }

}
