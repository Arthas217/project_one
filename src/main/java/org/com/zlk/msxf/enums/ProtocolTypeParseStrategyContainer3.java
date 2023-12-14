package org.com.zlk.msxf.enums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/12/14 21:07
 */
@Component
public class ProtocolTypeParseStrategyContainer3 {

    @Autowired
    private List<ProtocolTypeStrategy3> typeStrategy3List;

    public String parse3(ProtocolTypeParseContext3 context3) {
        return typeStrategy3List.stream().filter(strategy -> strategy.support(context3)).findAny().map(strategy -> strategy.parse3(context3)).orElse(null);
    }
}
