package org.com.zlk.msxf.enums;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/12/13 18:55
 */
@Component
public class ProtocolTypeParseStrategyContainer {

    public final static Map<ProtocolTypeEnum, ProtocolTypeStrategy> ENUM_STRATEGY_MAP = new HashMap<>();

    @PostConstruct
    public void init(){
        ENUM_STRATEGY_MAP.put(ProtocolTypeEnum.HTTP,new HttpProtocolTypeParseStrategy());
        ENUM_STRATEGY_MAP.put(ProtocolTypeEnum.HTTPS,new HttpsProtocolTypeParseStrategy());
    }

    public String parse(ProtocolTypeParseContext context){
        return Optional.ofNullable(ENUM_STRATEGY_MAP.get(context.getTypeEnum())).map(strategy-> strategy.parse(context)).orElse(null);
    }

}
