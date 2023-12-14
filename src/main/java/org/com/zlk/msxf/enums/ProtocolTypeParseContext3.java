package org.com.zlk.msxf.enums;

import lombok.Data;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/12/14 21:01
 */
@Data
public class ProtocolTypeParseContext3 {

    private ProtocolTypeEnum typeEnum;

    private ProtocolTypeStrategy3 typeStrategy3;

    public String parse3() {
        return typeStrategy3.parse3(this);
    }
}
