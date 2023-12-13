package org.com.zlk.msxf.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 会游泳的蚂蚁
 * @description: 枚举遍历方式改成map方式
 * @date 2023/12/13 10:23
 */
public enum ProtocolTypeEnum {

    HTTP(1, "http"),
    HTTPS(2, "https"),
    WEBSOCKET(3, "websocket"),
    TCP(4, "tco");

    private int code;
    private String desc;

    ProtocolTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


    private static Map<Integer, ProtocolTypeEnum> CODE_MAP = Arrays.stream(ProtocolTypeEnum.values()).collect(Collectors.toMap(ProtocolTypeEnum::getCode, Function.identity()));
    private static Map<String, ProtocolTypeEnum> NAME_MAP = Arrays.stream(ProtocolTypeEnum.values()).collect(Collectors.toMap(ProtocolTypeEnum::name, Function.identity()));

    public static ProtocolTypeEnum getByCode(int code) {
        return CODE_MAP.get(code);
    }

    public static String parseDesc(int code) {
        return Optional.ofNullable(getByCode(code)).map(ProtocolTypeEnum::getDesc).orElse(null);
    }

    public static ProtocolTypeEnum getByName(String name) {
        return NAME_MAP.get(name);
    }

    public static String parseName(String name) {
        return Optional.ofNullable(getByName(name)).map(ProtocolTypeEnum::getDesc).orElse(null);
    }

    public static void main(String[] args) {
        String name = ProtocolTypeEnum.HTTP.name();
        int code = ProtocolTypeEnum.HTTP.getCode();
        String desc = ProtocolTypeEnum.HTTP.getDesc();
        System.out.println(String.format("枚举的name=%s,code=%s,desc=%s", name, code, desc));
        System.out.println(String.format("通过枚举code值，返回desc方式：%s",parseDesc(code)));
        System.out.println(String.format("通过枚举name值，返回desc方式：%s",parseName(name)));

    }


}

