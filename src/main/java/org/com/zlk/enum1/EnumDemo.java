package org.com.zlk.enum1;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/8/2 15:51
 */
public class EnumDemo {

    public static void main(String[] args) {
        System.out.println(BaseEnum.valueOf(ErrorCodeEnum.class, 0).getMsg());
        System.out.println(BaseEnum.enum2List(ErrorCodeEnum.class));
    }
}
