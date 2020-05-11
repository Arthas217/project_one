package org.com.zlk.genericstype;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过反射 绕过泛型编译器的检测
 */
public class TypeErasureSpecialCase {

    public static void main(String[] args) {
        List<Integer> ls = new ArrayList<>();
        ls.add(23);
        try {
            Method method = ls.getClass().getDeclaredMethod("add", Object.class);
            method.invoke(ls, "test");
            method.invoke(ls, 42.9f);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Object o : ls) {
            System.out.println(o);
        }

    }


}
