package org.com.zlk.msxf.enums;

import lombok.Data;

/**
 * @author 会游泳的蚂蚁
 * @description: 协议解析上下文
 * 构造一个上下文，策略本身基于上下文来处理，借助上文定义的值枚举做策略路由。
 * @date 2023/12/13 12:41
 */
@Data
public class ProtocolTypeParseContext {

    private ProtocolTypeEnum typeEnum;

    private ProtocolTypeStrategy typeStrategy;

    public String parse(){
        return typeStrategy.parse(this);
    }
}
