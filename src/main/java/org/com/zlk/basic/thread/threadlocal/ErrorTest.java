package org.com.zlk.basic.thread.threadlocal;

import lombok.Getter;
import lombok.Setter;


/**
 * 多线程 操作content共有资源问题
 */
@Getter
@Setter
public class ErrorTest {

    private String content;

    public static void main(String[] args) {
        ErrorTest test = new ErrorTest();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                test.setContent(Thread.currentThread().getName() + "的数据");
                System.out.println("---------------");
                System.out.println(Thread.currentThread().getName() + " 获得数据 " + test.getContent());
            });
            thread.start();
        }
    }
}
