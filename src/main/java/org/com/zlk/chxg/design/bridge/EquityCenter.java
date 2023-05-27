package org.com.zlk.chxg.design.bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2023/5/27 20:47
 */
public class EquityCenter implements ICardList{
    @Override
    public List<CreditCard> queryCardList(String cisNo,String token) {
        System.out.println("权益中心，通过客编调用RPC服务获取该用户的所有信用卡信息---卡状态为1并且产品编码prodno不为0");
        System.out.println("权益客编："+cisNo +"token:"+token);
        return new ArrayList<>();
    }
}
