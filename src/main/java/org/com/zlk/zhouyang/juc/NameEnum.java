package org.com.zlk.zhouyang.juc;

import lombok.Getter;

public enum NameEnum {

    NAME1(1, "张三"),
    NAME2(2, "王五"),
    NAME3(3, "李四"),
    NAME4(4, "赵一"),
    NAME5(5, "刘二"),
    NAME6(6, "左六");


    @Getter
    private int code;
    @Getter
    private String message;

    NameEnum(int key, String name) {
        this.code = key;
        this.message = name;
    }

    public static NameEnum getName(int key) {
        NameEnum[] nameEnums = NameEnum.values();
        for (NameEnum nameEnum : nameEnums) {
            if (nameEnum.getCode() == key) {
                return nameEnum;
            }
        }
        return null;
    }
}
