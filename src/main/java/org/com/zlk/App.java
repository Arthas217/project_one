package org.com.zlk;

import org.com.zlk.annotation.lombook.Man;
import org.com.zlk.offer.Singleton;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // 单例
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton);
    }
}
