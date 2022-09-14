package org.com.zlk.java8.book;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * 虽然本例中的构造方法是私有的，但使用哪种访问权限实际上区别并不大：
 * 构造方法只能用来创建你在枚举定义中声明的枚举实例；在枚举定义完成后，编译器不会允许你用它来创建任何新的类型。
 * @Date 2022/9/14 16:11
 */
public enum OzWitchEnum {

    WEST("Miss Gulch, aka the Wicked Witch of the West"),
    NORTH("Glinda, the Good Witch of the North"),
    EAST("Wicked Witch of the East, wearer of the Ruby Slippers, crushed by Dorothy's house"),
    SOUTH("Good by inference, but missing");

    private String description;

    // 构造器的访问权限必须是包级或private：
    private OzWitchEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
