package org.com.zlk.msxf.enums;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/12/13 12:46
 */
public class HttpProtocolTypeParseStrategy implements ProtocolTypeStrategy{
    @Override
    public String parse(ProtocolTypeParseContext context) {
        return ProtocolTypeEnum.HTTP.getDesc();
    }
}
