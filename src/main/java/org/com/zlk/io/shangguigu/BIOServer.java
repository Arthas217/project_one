package org.com.zlk.io.shangguigu;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author zc217
 * @Date 2021/1/6
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        //1、创建一个线程池
        //2、如果有客户端连接，就创建一个线程，与之通讯（单独写一个方法）
        ExecutorService executorService = Executors.newCachedThreadPool();

        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了");

        while (true) {
            System.out.println("线程信息：id= " + Thread.currentThread().getId() + "; 线程名字：" + Thread.currentThread().getName());
            //监听，等待客户端连接
            System.out.println("等待连接");
            //会有阻塞
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            //创建一个线程，与之通讯
            executorService.execute(() -> {
                //重写Runnable方法，与客户端进行通讯
                handler(socket);
            });
        }
    }

    //编写一个Handler方法，和客户端通讯
    public static void handler(Socket socket) {
        try {
            System.out.println("线程信息：id= " + Thread.currentThread().getId() + "; 线程名字：" + Thread.currentThread().getName());
            //存储数据
            byte[] bytes = new byte[1024];
            //通过socket获取输入流、从管道读取数据
            InputStream inputStream = socket.getInputStream();
            //循环的读取客户端发送的数据
            while (true) {
                System.out.println("线程信息：id= " + Thread.currentThread().getId() + "; 线程名字：" + Thread.currentThread().getName());
                System.out.println("read...."+Thread.currentThread().getName());
                //输入流读取到bytes中，会有阻塞情况
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));//输出客户端发送的数据
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
