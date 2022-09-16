package org.com.zlk.serializable;

import org.apache.ibatis.cache.CacheException;

import java.io.*;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/15 14:54
 */
public final class SerializeUtil {

    public static byte[] serialize(Object object) {
        ObjectOutputStream oos;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }


    public static Object unserialize(byte[] bytes) {
        if (bytes == null) {
            return bytes;
        }
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            ois.close();
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new CacheException(e);
        }
//        return "1";
    }
}
