package org.com.zlk.chxg.design.bridge;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 权益中心对客展示vo
 * @Date 2023/5/27 20:37
 */
public class Rights implements Serializable {

    private String cisNo;
    private String realName;
    private String highLevel;
    private List<CreditCard> cardList;
    private String mLevel;

    public String getCisNo() {
        return cisNo;
    }

    public void setCisNo(String cisNo) {
        this.cisNo = cisNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHighLevel() {
        return highLevel;
    }

    public void setHighLevel(String highLevel) {
        this.highLevel = highLevel;
    }

    public List<CreditCard> getCardList() {
        return cardList;
    }

    public void setCardList(List<CreditCard> cardList) {
        this.cardList = cardList;
    }

    public String getmLevel() {
        return mLevel;
    }

    public void setmLevel(String mLevel) {
        this.mLevel = mLevel;
    }
}
