package org.com.zlk.basic.serialize;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
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
