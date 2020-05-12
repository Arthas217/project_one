package org.com.zlk.basic.thread.threadlocal;

/**
 * 线程隔离方式 可以
 * synchronize 也可以
 */
public class ThreadLocalSolve {

    private ThreadLocal<String> threadLocal = new ThreadLocal();

    public String getContent() {
       return threadLocal.get();
    }

    public void setContent(String content) {
        threadLocal.set(content);
    }

    public static void main(String[] args) {
        ThreadLocalSolve test = new ThreadLocalSolve();
        int i = 0;
        while (i < 10) {
            Thread thread = new Thread(() -> {
                test.setContent(Thread.currentThread().getName() + "的数据");
                System.out.println("---------------");
                System.out.println(Thread.currentThread().getName() + " 获得数据 " + test.getContent());
            });
            thread.start();
            i++;
        }

    }
}
