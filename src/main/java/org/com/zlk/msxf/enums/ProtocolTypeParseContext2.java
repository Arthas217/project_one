package org.com.zlk.msxf.enums;

import lombok.Data;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/12/14 12:14
 */
@Data
public class ProtocolTypeParseContext2 {

    private ProtocolTypeEnum typeEnum;

    private ProtocolTypeStrategy2 typeStrategy;

    public String parse2(){
        return typeStrategy.parse2(this);
    }
}
