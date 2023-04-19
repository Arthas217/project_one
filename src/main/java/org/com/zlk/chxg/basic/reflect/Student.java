package org.com.zlk.chxg.basic.reflect;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 实体类，JavaReflect2使用
 * @Date 2022/11/8 14:44
 */
public class Student {

    //---------------构造方法-------------------

    //默认的构造方法
    Student(String str) {
        System.out.println("(默认)的构造方法 s = " + str);
    }

    //无参构造方法
    public Student() {
        System.out.println("调用了公有、无参构造方法执行了。。。");
    }

    //有一个参数的构造方法
    public Student(char sex) {
        this.sex = sex;
        System.out.println("调用了公有、有参构造方法,参数 sex=" + sex);
    }

    //有多个参数的构造方法
    public Student(String name, int age) {
        System.out.println("调用了公有、有参构造方法,参数 name=" + name + " age=" + age);
    }

    //受保护的构造方法
    protected Student(boolean n) {
        System.out.println("调用了受保护的构造方法，参数 n= " + n);
    }

    //私有构造方法
    private Student(int age) {
        this.age = age;
        System.out.println("调用了私有的构造方法 参数 age=" + age);
    }


    //---------------成员变量-------------------

    public String name;
    protected int age;
    char sex;
    private String phoneNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", sex=" + sex
                + ", phoneNum=" + phoneNum + "]";
    }


    //---------------成员方法-------------------

    public void show1(String s) {
        System.out.println("调用了公有的，参数类型为String类型的show1()方法  参数=" + s);
    }

    protected void show2() {
        System.out.println("调用了受保护的，无参类型的show2()方法");
    }

    void show3() {
        System.out.println("调用了默认的，无参类型的show3()方法");
    }

    private String show4(int age) {
        System.out.println("调用了私有的，参数类型为int的show4()方法， 返回值是String 参数age= " + age);
        return "show4 method return type";
    }

}
