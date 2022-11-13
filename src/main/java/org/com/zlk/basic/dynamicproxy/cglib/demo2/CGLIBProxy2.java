package org.com.zlk.basic.dynamicproxy.cglib.demo2;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/11/13 08:59
 */
public class CGLIBProxy2 {

    public static void main(String[] args) {
        /**
         * 优点：基于字节码生成真实对象的子类；运行效率高于JDK 代理；真实对象不需要实现接口
         * 缺点：需要额外导入jar包
         *
         * cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理（子类也是动态代理对象）
         */
        UserManager userManagerCGLib = (UserManager)new CGLibProxy().createProxyObject(new UserManagerImpl());
        System.out.println("CGLibProxy：");
        userManagerCGLib.addUser("1", "chenXI");
    }
}
