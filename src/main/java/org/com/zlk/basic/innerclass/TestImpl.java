package org.com.zlk.basic.innerclass;

/**
 * 普通类
 */
public class TestImpl {

    //内部类（实现接口InterfaceTest）  也叫成员内部类
    private class InterfaceImpl implements InterfaceTest {
        @Override
        public void increment() {
            System.out.println("increment");
        }
    }

    // 该方法返回InterfaceTest实例，且并不知道这个实例是这么实现的，它可以很好的隐藏实现
    // 由于InterfaceImpl是private的，所以我们如果不看代码的话根本看不到这个具体类的名字
    public InterfaceTest getImpl() {
        return new InterfaceImpl();
    }


    //方法的参数是接口可以用 匿名内部类来实现
    public void method(InterfaceTest interfaceTest) {
        System.out.println("方法method");
    }


    //匿名内部类
    public interface ActionListener {
        public void onAction();
    }

    // 局部变量params 必须是final
    public void click(final int params) {
        //匿名内部类，实现的是ActionListener接口
        new ActionListener() {
            @Override
            public void onAction() {
                System.out.println(STATIC_INT);
                System.out.println(noStaticInt);
                outer_f3();
                outer_f4();
                System.out.println("click action..." + params);
            }
        }.onAction();
    }


    private int noStaticInt = 1;
    private static int STATIC_INT = 2;

    public void testFunctionClass(final int params) {
        //局部内部类（方法内部类） 在方法内部，只有方法调用并使用
        class FunctionClass {
            private void fun() {
                System.out.println("局部内部类的输出");
                System.out.println(STATIC_INT);
                System.out.println(noStaticInt);
                System.out.println(params);
                //params ++ ; // params 不可变所以这句话编译错误
            }
        }
        FunctionClass functionClass = new FunctionClass();
        functionClass.fun();
    }


    private static int i = 1;
    private int j = 10;
    private int k = 20;

    //成员内部类
    class Inner {
        //static int inner_i =100; //内部类中不允许定义静态变量
        int j = 100;//内部类中外部类的实例变量可以共存
        int inner_i = 1;

        void inner_f1() {
            System.out.println(i);//外部类的变量如果和内部类的变量没有同名的，则可以直接用变量名访问外部类的变量
            System.out.println(j);//在内部类中访问内部类自己的变量直接用变量名
            System.out.println(this.j);//也可以在内部类中用"this.变量名"来访问内部类变量
            //访问外部类中与内部类同名的实例变量可用"外部类名.this.变量名"。
            System.out.println(k);//外部类的变量如果和内部类的变量没有同名的，则可以直接用变量名访问外部类的变量
            outer_f1();
            outer_f2();
        }
    }

    public static void outer_f1() {
        //do more something
    }

    public void outer_f2() {
        //do more something
    }

    //外部类的非静态方法访问成员内部类
    public void outer_f3() {
        Inner inner = new Inner();
        inner.inner_f1();
    }

    //外部类的静态方法访问成员内部类，与在外部类外部访问成员内部类一样
    public static void outer_f4() {
        //step1 建立外部类对象
        TestImpl out = new TestImpl();
        //***step2 根据外部类对象建立内部类对象***
        Inner inner = out.new Inner();
        //step3 访问内部类的方法
        inner.inner_f1();

    }


}
