package org.com.zlk.io.shangguigu;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Author 会游泳的蚂蚁
 * @Description: Unpooled获取Netty的数据容器ByteBuf
 * @Date 2021/1/16 20:42
 */
public class NettyByteBuf01 {
    public static void main(String[] args) {
        //1. 创建一个ByteBuf，该对象包含一个数组byte[10]
        //2. netty的buffer中，不需要使用flip进行反转, 底层维护了 readerindex （下次读的位置）和 writerIndex（下次写的位置）
        //3. 通过readerindex和writerIndex和capacity，将buffer分成三个区域
        // 0---readerindex 已经读取的区域
        // readerindex---writerIndex可读的区域
        // writerIndex -- capacity可写的区域
        ByteBuf buffer = Unpooled.buffer(10);
        //输入（writerIndex变化从0-10)
        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }
        System.out.println("capacity=" + buffer.capacity());//10
        //输出（指定i索引获取可读数据 readerindex 不会变化
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.print(buffer.getByte(i));
        }
        System.out.println("------------");
        // 输出 readerindex 会变化0-10
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.print(buffer.readByte());
        }
        System.out.println("------------");
        System.out.println("执行完毕");
    }
}
