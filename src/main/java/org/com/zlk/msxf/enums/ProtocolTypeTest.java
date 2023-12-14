package org.com.zlk.msxf.enums;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.com.zlk.msxf.enums.ProtocolTypeEnum.*;

/**
 * @author 会游泳的蚂蚁
 * @description: 一段代码引发的思考
 * https://mp.weixin.qq.com/s/Vt_mdLicWwkZ8phD1rH6UQ
 * @date 2023/12/13 12:49
 */
public class ProtocolTypeTest {

    public static void main(String[] args) {
        String name = ProtocolTypeEnum.HTTP.name();
        int code = ProtocolTypeEnum.HTTP.getCode();
        String desc = ProtocolTypeEnum.HTTP.getDesc();
        System.out.println(String.format("枚举的name=%s,code=%s,desc=%s", name, code, desc));
        System.out.println(String.format("通过枚举code值，返回desc方式：%s", parseDesc(code)));
        System.out.println(String.format("通过枚举name值，返回desc方式：%s", parseName(name)));
        System.out.println(String.format("通过策略输入code值，返回desc方式%s", parseProtocolType(HTTPS.getCode())));

        ProtocolTypeParseStrategyContainer container = new ProtocolTypeParseStrategyContainer();
        container.init();// 模拟bean注入
        ProtocolTypeParseContext context = new ProtocolTypeParseContext();
        context.setTypeEnum(HTTPS);
        System.out.println(String.format("通过中间层容器工厂解耦一下依赖(调用方，策略实现的路由过程),手动容器方式，返回desc方式%s", container.parse(context)));


        ProtocolTypeParseStrategyContainer2 container2 = new ProtocolTypeParseStrategyContainer2();
        container2.init();
        ProtocolTypeParseContext2 context2 = new ProtocolTypeParseContext2();
        context2.setTypeEnum(HTTPS);
        System.out.println(String.format("容器移动至策略内部，改成由策略实现自身去注册到容器中，返回desc方式%s", container2.parse2(context2)));

    }

}
