package org.com.zlk.msxf.enums;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/12/14 11:57
 */
public interface ProtocolTypeStrategy2 {

    String parse2(ProtocolTypeParseContext2 saleTypeParseContext);

    // 所支持的情况
    ProtocolTypeEnum support();
}
