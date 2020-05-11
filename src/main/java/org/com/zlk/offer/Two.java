package org.com.zlk.offer;

public class Two {

    private static volatile Two instance;

    private Two() {
    }

    public Two getInstance() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = new Two();
                }
            }
        }
        return instance;
    }
}