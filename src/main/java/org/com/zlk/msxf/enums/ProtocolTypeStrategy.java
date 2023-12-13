package org.com.zlk.msxf.enums;

/**
 * @author 会游泳的蚂蚁
 * @description: 基于ProtocolTypeEnum的策略接口，引入策略上下文SaleTypeParseContext
 * https://mp.weixin.qq.com/s/Vt_mdLicWwkZ8phD1rH6UQ
 * @date 2023/12/13 12:39
 */
public interface ProtocolTypeStrategy {

    String parse(ProtocolTypeParseContext context);
}
