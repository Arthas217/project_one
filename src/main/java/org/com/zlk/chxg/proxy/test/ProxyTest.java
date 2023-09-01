package org.com.zlk.chxg.proxy.test;

import org.com.zlk.chxg.proxy.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/8/31 23:53
 */
public class ProxyTest {

    public static void main(String[] args) throws Exception {
        //有代理的地方就有反射
//        testReflect();
        //JDK代理
//        testJDKProxy();
        //CGLIB代理
//        testCGLIBProxy();
        //不懂的
//        testASMProxy();
//        testJavassistProxy();
        testByteBuddyProxy();
    }

    private static void testByteBuddyProxy() throws Exception {
        IUserApi userApi = ByteBuddyProxy.getProxy(UserApi.class);
        String invoke = userApi.queryUserInfo();
        System.out.println(("测试结果：" + invoke));
    }

    private static void testJavassistProxy() throws Exception {
        IUserApi userApi = JavassistProxy.getProxy(UserApi.class);
        String invoke = userApi.queryUserInfo();
        System.out.println(("测试结果：" + invoke));
    }

    private static void testASMProxy() throws Exception {
        IUserApi userApi = ASMProxy.getProxy(UserApi.class);
        String invoke = userApi.queryUserInfo();
        System.out.println(("测试结果：" + invoke));
    }

    private static void testCGLIBProxy() {
        CglibProxy cglibProxy = new CglibProxy();
        UserApi userApi = (UserApi) cglibProxy.newInstance(new UserApi());
        String invoke = userApi.queryUserInfo();
        System.out.println(("测试结果：" + invoke));
    }

    private static void testJDKProxy() {
        IUserApi jdkProxy = JdkProxy.getJDKProxy(IUserApi.class);
        String info = jdkProxy.queryUserInfo();
        System.out.println(info);
    }

    private static void testReflect() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<UserApi> clazz = UserApi.class;
        Method queryUserInfo = clazz.getMethod("queryUserInfo");
        Object invoke = queryUserInfo.invoke(clazz.newInstance());
        System.out.println("通过反射方式，invoke了UserApi#queryUserInfo ："+invoke);
    }

}
