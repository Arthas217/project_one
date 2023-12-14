package org.com.zlk.msxf.enums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/12/14 12:02
 */
@Component
public class ProtocolTypeParseStrategyContainer2 {

    public final static Map<ProtocolTypeEnum, ProtocolTypeStrategy2> ENUM_STRATEGY_MAP = new HashMap<>();

    @Autowired
    private List<ProtocolTypeStrategy2> parseStrategyList2;

    @PostConstruct
    public void init() {
        //由策略实现自身去注册到容器中
        parseStrategyList2.stream().forEach(strategy -> ENUM_STRATEGY_MAP.put(strategy.support(), strategy));
    }

    public String parse2(ProtocolTypeParseContext2 context) {
        return Optional.ofNullable(ENUM_STRATEGY_MAP.get(context.getTypeEnum())).map(strategy -> strategy.parse2(context)).orElse(null);
    }
}
