package org.com.zlk.basic.innerclass;

public class Test {
    public static void main(String[] args) {
        TestImpl t = new TestImpl();
        InterfaceTest interfaceTest = t.getImpl();
        interfaceTest.increment();

        MainExample m = new MainExample();
        System.out.println("姓名:"+m.name());
        System.out.println("年龄:"+m.age());


        // Java8 引入 lambda 表达式可以让代码更简洁
        t.method(new InterfaceTest() {
            @Override
            public void increment() {
                System.out.println("实现接口");
            }
        });
        t.method(() -> System.out.println("实现接口"));

        t.click(0);

        t.testFunctionClass(1);

    }
}
