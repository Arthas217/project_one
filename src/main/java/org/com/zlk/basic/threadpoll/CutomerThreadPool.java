package org.com.zlk.basic.threadpoll;

import java.util.concurrent.*;

/**
 * 定义单例线程池
 */
public class CutomerThreadPool {

    private static CutomerThreadPool pool = null;


    // 这里 单例线程池中有两种具体的线程池
    private ThreadPoolExecutor threadPool = null;
    private ScheduledThreadPoolExecutor scheduledThreadPool = null;

    private int corePoolSize = 10;
    private int maximumPoolSize =20;
    private long keepAliveTime = 3;
    private int scheduledPoolSize = 10;


    /**
     * 对外提供instance
     */
    public static CutomerThreadPool getPoolInstance() {
        if (pool == null) {
            creat();
        }
        return pool;
    }

    public ThreadPoolExecutor getThreadPool(){
        return  threadPool;
    }

    public ScheduledThreadPoolExecutor  getScheduledThreadPool(){
        return scheduledThreadPool;
    }

    //构造函数
    private CutomerThreadPool() {
       this.threadPool = new ThreadPoolExecutor(corePoolSize,
               maximumPoolSize,
               keepAliveTime,
               TimeUnit.SECONDS,
               new LinkedBlockingQueue<>(),
               new ThreadPoolExecutor.CallerRunsPolicy()); // 由调用线程处理该任务
       this.scheduledThreadPool = new ScheduledThreadPoolExecutor(scheduledPoolSize);
    }

    private static synchronized void creat() {
        if (pool == null) {
            pool = new CutomerThreadPool();
        }
    }
}
