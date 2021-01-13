package org.com.zlk.io.shangguigu.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Author 会游泳的蚂蚁
 * @Description: java IO 的客户端
 * @Date 2021/1/12 15:10
 */
public class OldIOClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 7001);
        String fileName = "/Users/zuocheng/Downloads/dubbo-2.5.x.zip";
        InputStream inputStream = new FileInputStream(fileName);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] buffer = new byte[4096];
        long readCount;
        long total = 0;

        long startTime = System.currentTimeMillis();

        while ((readCount = inputStream.read(buffer)) >= 0) {
            total += readCount;
            dataOutputStream.write(buffer);
        }
        System.out.println("发送总字节数： " + total + ", 耗时： " + (System.currentTimeMillis() - startTime));
        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }
}
