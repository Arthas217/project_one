package org.com.zlk.basic.innerclass;

public class MainExample {

    // 内部类1
    private class Test1 extends ClassA{
        public String name(){
            System.out.println("Test1 --继承---ClassA");
            return super.name();
        }
    }
    // 内部类2
    private class Test2 extends ClassB{
        public int age(){
            System.out.println("Test2 --继承---ClassB");
            return super.age();
        }
    }

    public String name(){
        return new Test1().name();
    }
    public int age(){
        return new Test2().age();
    }
}
