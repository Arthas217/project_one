package org.com.zlk.basic;


import java.util.Objects;

public class Key {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public Key(int id) {
        this.id = id;
    }

    /**
     * 自定义的计算方式（值方便看） 建议使用Objects.hash方法
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * 自定义方式 比价两个对象中的值是否相同
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Key)) {
            return false;
        } else {
            return this.getId().equals(((Key) obj).getId());
        }
    }
}


