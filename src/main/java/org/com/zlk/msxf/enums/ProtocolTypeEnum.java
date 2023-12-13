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

    /* code方式*/
    public static String parseDesc(int code) {
        return Optional.ofNullable(getByCode(code)).map(ProtocolTypeEnum::getDesc).orElse(null);
    }

    public static ProtocolTypeEnum getByName(String name) {
        return NAME_MAP.get(name);
    }

    /* name方式*/
    public static String parseName(String name) {
        return Optional.ofNullable(getByName(name)).map(ProtocolTypeEnum::getDesc).orElse(null);
    }


    /* 策略模式，简单实现方式*/
    public static String parseProtocolType(int code){
        ProtocolTypeEnum protocolTypeEnum = ProtocolTypeEnum.getByCode(code);
        ProtocolTypeParseContext context= new ProtocolTypeParseContext();
        context.setTypeEnum(protocolTypeEnum);
        switch (code){
            case 1:
                context.setTypeStrategy(new HttpProtocolTypeParseStrategy());
                break;
            case 2:
                context.setTypeStrategy(new HttpsProtocolTypeParseStrategy());
                break;
            default:
                return null;
        }
        return context.parse();
    }

}

