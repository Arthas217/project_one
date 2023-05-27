package org.com.zlk.chxg.design.bridge;

import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 权益中心对客展示接口的实现类
 * @Date 2023/5/27 20:55
 */
public class RightsAndInterestsImpl extends AbstractRightsAndInterests {

    private String cisNo;
    private String token;

    /**
     * 构造方法
     * @param iCardList  获取信用卡列表接口
     * @param cisNo
     * @param token
     */
    public RightsAndInterestsImpl(ICardList iCardList, String cisNo, String token) {
        super(iCardList);
        this.cisNo = cisNo;
        this.token = token;
    }

    /**
     * DSF数据交换和通信，获取得到积分、白金、私人银行、过滤网办，机场高铁等过程
     * @return
     */
    @Override
    public Rights queryRights() {
        List<CreditCard> creditCardList = iCardList.queryCardList(cisNo, token);
        System.out.println("通过卡列表获取该用户所拥有的权益的过滤.....");
        return new Rights();
    }
}
