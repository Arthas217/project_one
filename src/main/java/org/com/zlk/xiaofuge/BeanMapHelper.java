package org.com.zlk.xiaofuge;

import lombok.Getter;
import lombok.Setter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/10/14 20:40
 */
public class BeanMapHelper {

    public static Object Bean2Bean(Object src, Class<?> desClass) {
        try {
            Object result = desClass.newInstance();
            for (Map.Entry<String, Object> entry : objectToMap(src).entrySet()) {
                for (Map.Entry<String, Object> entry1 : objectToMap(result).entrySet()) {
                    if (entry.getKey().equals(entry1.getKey())) {
                        entry1.setValue(entry.getValue());
                    }
                }
            }
            result = mapToObject(objectToMap(src), desClass);
            return result;
        } catch (Exception e) {
            System.err.println("转换异常");
        }
        return null;
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) {
            return null;
        }

        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }
        return map;
    }

    @Getter
    @Setter
    static class Person {
        int id;
        String name;
        int age;
        String sex;
    }

    @Getter
    @Setter
    static class Man {
        int id;
        String name;
        String sex;
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.setId(1);
        person.setName("方世玉");
        person.setAge(20);
        person.setSex("男");
        Object bean2Bean = Bean2Bean(person, Man.class);
        Man  man = (Man)bean2Bean;
        System.out.println(man.getId() +" "+man.getName()+" "+man.getSex());
    }


}
