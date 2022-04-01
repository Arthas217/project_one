package org.com.zlk.zdjx.sensitive;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/3/22 12:49
 */
public class SensitiveTest {

    /**
     * Description: 序列化对象
     */
    private static void SerializeUser() throws IOException {
        User user = new User();
        user.setName("迪丽热巴");
        /** ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作 **/
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("./user"));
        oo.writeObject(user);
        System.out.println("对象序列化成功！");
        oo.close();
    }

    /**
     * Description: 反序列对象
     */
    private static User DeserializeUser() throws Exception, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./user"));
        User user = (User) ois.readObject();
        System.out.println("user对象反序列化成功！");
        System.out.println(user.getName());
        return user;
    }

    public static void main(String[] args) {

        Map<String,?> map = new HashMap<>();
        Object o = map.get("data_flag" + "");
        System.out.println(o);
        try {
//            SerializeUser();
//            DeserializeUser();
        } catch  (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("=====");
        }
    }
}
