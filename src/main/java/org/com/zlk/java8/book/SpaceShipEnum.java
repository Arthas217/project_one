package org.com.zlk.java8.book;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/14 16:25
 */
public enum SpaceShipEnum {
    SCOUT, CARGO, TRANSPORT,
    CRUISER, BATTLESHIP, MOTHERSHIP;

    @Override
    public String toString() {
        String id = name();
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0) + lower;
    }
}
