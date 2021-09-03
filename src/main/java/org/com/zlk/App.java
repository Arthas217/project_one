package org.com.zlk;


import java.io.IOException;
import java.util.Random;

/**
 * Hello world!
 */
public class App {

    public static String VALUE = "静态变量";
    public static final String FINAL_VALUE = "静态常量";
    public static final int FINAL_VALUE_INT = new Random(10).nextInt();

    static {
        System.out.println(App.VALUE);
        System.out.println("类加载了");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        List list = sqlSession.selectList("com.foo.bean.BlogMapper.queryAllBlogInfo");

        System.out.println(App.VALUE);
        System.out.println(App.FINAL_VALUE);
        System.out.println(App.FINAL_VALUE_INT);


        String str1 = "abc";
        String str2 = new String("def");
        String str3 = "abc";
        String str4 = str2.intern();
        String str5 = "def";
        System.out.println(str1 == str3);//true
        System.out.println(str2 == str4);//false
        System.out.println(str4 == str5);//true

    }
}
