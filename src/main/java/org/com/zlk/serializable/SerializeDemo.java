package org.com.zlk.serializable;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * https://blog.csdn.net/jj89929665/article/details/123280707
 * @Date 2022/9/15 15:27
 */
public class SerializeDemo {

    public static void main(String[] args) {

//        byte[] zuo = "zuo".getBytes(StandardCharsets.UTF_8);//Java.io.EOFException

        // 需要成对使用
        byte[] zuo = SerializeUtil.serialize("zuo");
        System.out.println(zuo);
        Object unserialize = SerializeUtil.unserialize(zuo);
        System.out.println(unserialize);
    }
}
