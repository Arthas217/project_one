package org.com.zlk.chxg.design.bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2023/5/27 20:50
 */
public class BillCalender implements ICardList{
    @Override
    public List<CreditCard> queryCardList(String cisNo, String token) {
        System.out.println("账单日历通过客编获取该用户信用卡的卡信息----卡状态为2 并且产品编码prodno为1");
        System.out.println("账单客编："+cisNo +"token:"+token);
        return new ArrayList<>();
    }
}
