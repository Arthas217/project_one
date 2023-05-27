package org.com.zlk.chxg.design.bridge;

import java.io.Serializable;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 卡信息vo
 * @Date 2023/5/27 20:30
 */
public class CreditCard implements Serializable {

    private String  cardNo;
    private String  cardType;
    private String  cardOrganization;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardOrganization() {
        return cardOrganization;
    }

    public void setCardOrganization(String cardOrganization) {
        this.cardOrganization = cardOrganization;
    }
}
