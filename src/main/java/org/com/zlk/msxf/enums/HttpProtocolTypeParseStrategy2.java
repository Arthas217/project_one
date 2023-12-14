package org.com.zlk.msxf.enums;

import org.springframework.stereotype.Component;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/12/14 12:00
 */
@Component
public class HttpProtocolTypeParseStrategy2 implements ProtocolTypeStrategy2{
    @Override
    public String parse2(ProtocolTypeParseContext2 parseContext2) {
        return ProtocolTypeEnum.HTTP.getDesc();
    }

    @Override
    public ProtocolTypeEnum support() {
        return ProtocolTypeEnum.HTTP;
    }
}
