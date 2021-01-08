package org.com.zlk.io.shangguigu;

import java.nio.IntBuffer;

/**
 * @Author zc217
 * @Date 2021/1/6
 */
public class BasicBuffer {
    public static void main(String[] args) {
        //举例 Buffer 的使用 (简单说明)
        //即可以存放 5 个 int
        IntBuffer allocate = IntBuffer.allocate(5);
        //向 buffer 存放数据
        for (int i = 0; i < allocate.capacity(); i++) {
            allocate.put(i * 2);
        }
        //如何从buffer读取数据
        //将buffer转换，读写切换(!!!)
        allocate.flip();
//        allocate.position(1);
//        allocate.limit(3);
//        System.out.println(allocate.hasArray());
//        System.out.println(allocate.array());
        while (allocate.hasRemaining()) {
            System.out.println(allocate.get());
        }
    }
}
