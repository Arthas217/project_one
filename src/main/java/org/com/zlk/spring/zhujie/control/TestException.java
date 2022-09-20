package org.com.zlk.spring.zhujie.control;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/20 09:14
 */
public class TestException extends RuntimeException{

    /**
     * 异常信息
     */
    private String errorMsg;
    /**
     * 错误码
     */
    private String innerCode;

    private int code;

    public String getInnerCode() {
        return innerCode;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }


    public TestException(String errorMsg) {
        super(errorMsg);
        this.code = 500;
        this.innerCode = "5000";
        this.errorMsg = errorMsg;
        this.errorMsg = "Internal Server Error: "+errorMsg;

    }

    public TestException(Integer innerCode, String errorMsg) {
        super(errorMsg);
        this.code = 500;
        this.innerCode = "500"+innerCode.toString();
        this.errorMsg = errorMsg;
        this.errorMsg = "Test Internal Server Error: "+errorMsg;

    }

    /**
     * 抛出逻辑异常的两个静态类，对应上面两种构造方法
     * @param errorMsg
     * @return
     */
    public static TestException le(String errorMsg) {
        return new TestException(errorMsg);
    }
    public static TestException le(Integer innerCode, String errorMsg) {
        return new TestException(innerCode,errorMsg);
    }

}
