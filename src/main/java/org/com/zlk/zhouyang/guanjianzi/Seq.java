package org.com.zlk.zhouyang.guanjianzi;

public class Seq {

    /**
     * 举例多线程环境代码顺序执行可能是
     * 1234
     * 2134
     * 1324
     * 4由于依赖性原因没法在第一位置
     */
    public void mySort() {
        int x = 11; //1
        int y = 12; //2
        x = x + 5;  //3
        y = x * x;  //4
    }


    int a = 0;
    boolean flag = false;

    /**
     * 多线程环境中线程交替执行，编译器优化重排的存在
     * 线程中使用的变量a和flag不能保证一致性。
     */
    public void method1() {
        // 下面两个语句没有依赖性,顺序可以调整
        // 禁止排序  将a和flag都添加volatile关键字
        a = 1;        //11
        flag = true;  //22
    }

    public void method2() {
        if (flag) {
            a = a + 5;
            if (a != 6) {
                System.out.println("------" + a);
            }
        }
    }

    public static void main(String[] args) {
        //举例 volatile禁止指令重排序
        Seq seq = new Seq();
        for (int i = 0; i < 2000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1; j++) {
                    seq.method1();
                    seq.method2();
                }
            }, String.valueOf(i)).start();
        }
    }
}
