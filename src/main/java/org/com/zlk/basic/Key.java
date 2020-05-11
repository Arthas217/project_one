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

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Key)) {
            return false;
        } else {
            return this.getId().equals(((Key) obj).getId());
        }
    }
}


