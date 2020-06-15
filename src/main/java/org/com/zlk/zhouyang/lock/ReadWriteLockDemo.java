package org.com.zlk.zhouyang.lock;

/**
 * 读写锁  （共享读、独占写）
 * 数据一致性
 * 提高并发性 多个线程可以同时读取共享资源  之前的锁不论是读还是写都是对共享资源的独占操作
 * 如果一个线程想写共享资源，那么就不应该有其他线程对资源进行读或写
 * 所以只有读读可以共存。
 */
public class ReadWriteLockDemo {

    // 注意如果不加锁(MyCache中不使用变量readWriteLock)时，对于输出的结果 0写入完成之前中间存在很多操作（不符合原子+独占思想）
    // 添加读写锁后 读操作不需要严格控制，但是写的操作严格控制、保证了数据一致性，而且提高了并发性，读写分离。
    public static void main(String[] args) {
        MyCache cache = new MyCache();
        // 5个线程写
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.putOperation(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        // 5个线程读
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.getOperation(temp + "");
            }, String.valueOf(i)).start();
        }
    }

}
