package org.com.zlk.chxg.design.bridge;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 定义权益中心对客展示基础（公共）逻辑
 * @Date 2023/5/27 20:33
 */
public abstract class AbstractRightsAndInterests implements IRightsAndInterests {

    /**
     * 此处不能修饰权限定义为private，继承此类的子类无法通过此接口调用
     */
    protected ICardList iCardList;

    public AbstractRightsAndInterests(ICardList iCardList) {
        this.iCardList = iCardList;
    }

    public abstract Rights queryRights();
}
