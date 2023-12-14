package org.com.zlk.msxf.enums;

import org.springframework.stereotype.Component;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/12/14 21:05
 */
@Component
public class HttpProtocolTypeParseStrategy3 implements ProtocolTypeStrategy3 {
    @Override
    public String parse3(ProtocolTypeParseContext3 context3) {
        return ProtocolTypeEnum.HTTP.getDesc();
    }

    @Override
    public boolean support(ProtocolTypeParseContext3 context3) {
        return ProtocolTypeEnum.HTTP.equals(context3.getTypeEnum());
    }
}
