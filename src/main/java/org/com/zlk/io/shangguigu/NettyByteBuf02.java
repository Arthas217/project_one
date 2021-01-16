package org.com.zlk.io.shangguigu;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/1/16 21:01
 */
public class NettyByteBuf02 {
    public static void main(String[] args) {
        //创建 ByteBuf
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,world!", Charset.forName("utf-8"));
        // byteBuf空间是否分配
        if (byteBuf.hasArray()) { // true
            byte[] content = byteBuf.array();
            //将content转成字符串
            System.out.println(new String(content, Charset.forName("utf-8")));
            // byteBuf=UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 0, widx: 12, cap: 36)
            System.out.println("byteBuf=" + byteBuf);

            System.out.println(byteBuf.arrayOffset()); // 0
            System.out.println(byteBuf.readerIndex()); // 0
            System.out.println(byteBuf.writerIndex()); // 12
            System.out.println(byteBuf.capacity()); // 36

            // 可读字节数
            System.out.println("len=" + byteBuf.readableBytes());//12
            System.out.println(byteBuf.readByte()); // 104  h对应ASCII码
            System.out.println("len=" + byteBuf.readableBytes());//11
            System.out.println(byteBuf.getByte(0)); // 104
            System.out.println("len=" + byteBuf.readableBytes());//11

            //使用 for 取出各个字节
            for (int i = 0; i < byteBuf.readableBytes(); i++) {
                System.out.print((char) byteBuf.getByte(i));
            }
            System.out.println();
            //按照某个范围读取
            System.out.print(byteBuf.getCharSequence(0, 5, Charset.forName("utf-8")));
            System.out.println();
            System.out.println(byteBuf.getCharSequence(5, 7, Charset.forName("utf-8")));
        }
    }
}
