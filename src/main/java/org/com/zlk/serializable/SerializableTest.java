package org.com.zlk.serializable;

import java.io.*;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://blog.csdn.net/qq_34115899/article/details/118463573
 * 其他参考https://blog.csdn.net/tangnengwu/article/details/37901059
 * 测试1、父类没有序列化，程序报错java.io.NotSerializableException
 * 测试2、父类没有序列化，但是子类实现Serializable接口，父类没有实现，子类可以序列化！！这种情况父类一定要提供空构造方法(或者父类也实现序列化接口)，不要忘了子类的toString方法！
 * 测试3、引用People对象，People对象没有实现Serializable接口，有空构造函数,程序报错java.io.NotSerializableException
 * tips
 * 根据以上特性，我们可以将不需要被序列化的字段抽取出来放到父类中，子类实现 Serializable接口，父类不实现Serializable接口但提供一个空构造方法，则父类的字段数据将不被序列化。
 * @Date 2022/4/2 13:05
 */
// 屏蔽编译器的警告
@SuppressWarnings("all")
public class SerializableTest {
    /**
     * 序列化和反序列化
     */
    private static void testSerializable() throws Exception {
        // 序列化的步骤
        // 用于存储序列化的文件，这里的java_下划线仅仅为了说明是java序列化对象，没有任何其他含义
//        File file = new File("./people_10.java_");// 测试1
//        File file = new File("./son_10.java_"); // 测试2
        File file = new File("./comba_10.java_"); // 测试3
        if (!file.exists()) {
            // 1，先得到文件的上级目录，并创建上级目录
            file.getParentFile().mkdirs();
            try {
                // 2，再创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        People p = new People(10L);// 测试1
//        Son p = new Son(10L, "lcy", 18);//测试2
        Combo p = new Combo(1, new People(10L));//测试3
        // 创建一个输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        // 输出可序列化对象
        oos.writeObject(p);
        // 关闭输出流
        oos.close();

        // 反序列化的步骤
        // 创建一个输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        // 得到反序列化的对象，这里可以强转为People类型
        Object newObj = ois.readObject();
        // 关闭输入流
        ois.close();
        System.out.println(newObj);
    }

    public static void main(String[] args) throws Exception {
        testSerializable();
    }

}
