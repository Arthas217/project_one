package org.com.zlk.chxg.equity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2023/5/23 13:02
 */
public class RightInterestVO implements Serializable {

    private String realName;

    private List<CreditCardParam> paramList;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<CreditCardParam> getParamList() {
        return paramList;
    }

    public void setParamList(List<CreditCardParam> paramList) {
        this.paramList = paramList;
    }
}
