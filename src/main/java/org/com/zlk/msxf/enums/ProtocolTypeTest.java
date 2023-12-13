package org.com.zlk.msxf.enums;

import static org.com.zlk.msxf.enums.ProtocolTypeEnum.*;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/12/13 12:49
 */
public class ProtocolTypeTest {

    public static void main(String[] args) {
        String name = ProtocolTypeEnum.HTTP.name();
        int code = ProtocolTypeEnum.HTTP.getCode();
        String desc = ProtocolTypeEnum.HTTP.getDesc();
        System.out.println(String.format("枚举的name=%s,code=%s,desc=%s", name, code, desc));
        System.out.println(String.format("通过枚举code值，返回desc方式：%s", parseDesc(code)));
        System.out.println(String.format("通过枚举name值，返回desc方式：%s", parseName(name)));
        System.out.println(String.format("通过策略输入code值，返回desc方式%s", parseProtocolType(HTTPS.getCode())));

    }

}
