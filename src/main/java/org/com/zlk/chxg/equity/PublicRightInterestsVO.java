package org.com.zlk.chxg.equity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2023/5/23 13:01
 */
public class PublicRightInterestsVO implements Serializable {

    private String name;
    private List<String> prodNo;
    private List<String> cardBin;
    private List<String> cardLevel;
    private List<String> cardOrganization;



    public List<String> getProdNo() {
        return prodNo;
    }

    public void setProdNo(List<String> prodNo) {
        this.prodNo = prodNo;
    }

    public List<String> getCardBin() {
        return cardBin;
    }

    public void setCardBin(List<String> cardBin) {
        this.cardBin = cardBin;
    }

    public List<String> getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(List<String> cardLevel) {
        this.cardLevel = cardLevel;
    }

    public List<String> getCardOrganization() {
        return cardOrganization;
    }

    public void setCardOrganization(List<String> cardOrganization) {
        this.cardOrganization = cardOrganization;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
