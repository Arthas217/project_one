package org.com.zlk.chxg.design.bridge;

import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 信用卡卡列表接口
 * @Date 2023/5/27 20:43
 */
public interface ICardList {
    /**
     * 通过客编获取用户的所有信用卡的卡信息
     * @param cisNo
     * @return
     */
    List<CreditCard> queryCardList(String cisNo,String token);

}
