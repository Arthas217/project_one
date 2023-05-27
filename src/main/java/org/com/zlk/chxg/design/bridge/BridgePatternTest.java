package org.com.zlk.chxg.design.bridge;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 测试类
 * @Date 2023/5/27 21:11
 */
public class BridgePatternTest {

    public static void main(String[] args) {
        IRightsAndInterests equityCenter = new RightsAndInterestsImpl(new EquityCenter(),"123","ABC");
        IRightsAndInterests billCalender = new RightsAndInterestsImpl(new BillCalender(),"999","ABC");
        Rights rights = equityCenter.queryRights();
        Rights rights1 = billCalender.queryRights();
    }
}
