package org.com.zlk.msxf.enums;

/**
 * @author 会游泳的蚂蚁
 * @description: 当然如果不能事先知道“支持哪种情况”，只能在运行时判断“是否支持”，将事前判定改为运行时判定，广义责任链会是一个不错的选择，把所有策略排成一排，谁举手说自己能处理就谁处理。
 * @date 2023/12/14 20:59
 */
public interface ProtocolTypeStrategy3 {

    String parse3(ProtocolTypeParseContext3 context3);

    boolean support(ProtocolTypeParseContext3 context3);

}
