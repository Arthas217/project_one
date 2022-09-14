package org.com.zlk.java8.book;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/14 16:40
 */
public class TrafficLight {

    SignalEnum color = SignalEnum.RED;

    public void change() {
        switch(color) {
            // 注意在case语句中，无须使用Signal.RED
            case RED:    color = SignalEnum.GREEN;
                break;
            case GREEN:  color = SignalEnum.YELLOW;
                break;
            case YELLOW: color = SignalEnum.RED;
                break;
        }
    }


    @Override
    public String toString() {
        return "The traffic light is " + color;
    }

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight();
        for (int i = 0; i < 7; i++) {
            System.out.println(t);
            t.change();
        }
    }
}
