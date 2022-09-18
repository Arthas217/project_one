package org.com.zlk.serializable;

import java.io.*;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://blog.csdn.net/qq_34115899/article/details/118463573
 * 其他参考https://blog.csdn.net/tangnengwu/article/details/37901059
 * 测试1、父类没有序列化，程序报错java.io.NotSerializableException
 * 测试2、父类没有序列化，但是子类实现Serializable接口，父类没有实现，子类可以序列化！！这种情况父类一定要提供空构造方法(或者父类也实现序列化接口)，不要忘了子类的toString方法！
 * 测试3、引用People对象，People对象没有实现Serializable接口，有空构造函数,程序报错java.io.NotSerializableException,  一个类里面所有的属性必须是可序列化的，这个类才能顺利的序列化。
 * 测试4、对People对象，同一个对象多次序列化之间有属性更新。 反序列化读出的值都是一样，当对象第一次序列化成功后，后续这个对象属性即使有修改，也不会对后面的序列化造成成影响
 * tips
 * 根据以上特性，我们可以将不需要被序列化的字段抽取出来放到父类中，子类实现 Serializable接口，父类不实现Serializable接口但提供一个空构造方法，则父类的字段数据将不被序列化。
 * @Date 2022/4/2 13:05
 */
// 屏蔽编译器的警告
@SuppressWarnings("all")
public class SerializableDemo {
    private static void testSerializable() throws Exception {
        // 测试1 测试序列化、反序列化
//        File file = new File("./people_10.java_");

        // 测试2 子类实现Serializable接口，父类没有实现，子类可以序列化吗？
        // 父类Person不加空构造函数会报错,org.com.zlk.serializable.Son; no valid constructor,原来是因为父类没有序列化的时候，Object newWorker = ois.readObject()需要直接调用父类的无参数构造方法，不经过子类的无参构造方法。
        // 加了无参构造函数，但是子类不加toString此方法，打印的不是Worker，而是父类People，则调用父类toString方法，People{id=null}, 如果在无参构造初始化id则People{id=1}
        // 所以不需要被序列化的字段抽取出来放到父类中，子类实现 Serializable接口，子类实现toString方法，父类不实现Serializable接口但提供一个空构造方法，则父类的字段数据将不被序列化。
//        File file = new File("./son_10.java_");

        // 测试3 类中存在引用对象，这个类对象在什么情况下可以实现序列化？
        //org.com.zlk.serializable.People报异常，说明People类没有序列化,People加上implements Serializable后Combo{id=1, people=People{id=10}}
        //一个类里面所有的属性必须是可序列化的，这个类才能顺利的序列化。比如，类中存在引用对象，那么这个引用对象必须是可序列化的，这个类才能序列化。
//        File file = new File("./comba_10.java_");

        // 测试4 同一个对象多次序列化之间有属性更新，前后的序列化有什么区别？
        // 反序列化读出的值都是一样的。说明当对象第一次序列化成功后，后续这个对象属性即使有修改，也不会对后面的序列化造成成影响。
        // 序列化算法的原因，所有要序列化的对象都有一个序列化的编码号，当试图序列化一个对象，会检查这个对象是否已经序列化过，若从未序列化过，才会序列化为字节序列去输出。若已经序列化过，则会输出一个编码符号，不会重复序列化一个对象。
        // 序列化一次后，后续继续序列化并未重复转换为字节序列，而是输出字符q~
        File file = new File("./peopele_more.java_");
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
        // 序列化和反序列化
//        test1_2_3(file);
        test4(file);
    }

    private static void test4(File file) throws Exception {
        People p = new People(10L);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        // 未序列化，先修改属性
        p.setId(11L);
        oos.writeObject(p);
        // 序列化一次后，再次修改属性
        p.setId(15L);
        oos.writeObject(p);
        // 序列化两次后，再次修改属性
        p.setId(20L);
        oos.writeObject(p);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object people1 = ois.readObject();
        Object people2 = ois.readObject();
        Object people3 = ois.readObject();
        ois.close();

        System.out.println(((People) people1).getId());
        System.out.println(((People) people2).getId());
        System.out.println(((People) people3).getId());
    }

    private static void test1_2_3(File file) throws IOException, ClassNotFoundException {
        //序列化的步骤
//        People p = new People(10L);// 测试1
//        Son p = new Son(10L, "lcy", 18);//测试2
        Combo p = new Combo(1, new People(10L));//测试3 组合对象
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
