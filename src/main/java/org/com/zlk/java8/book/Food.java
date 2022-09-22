package org.com.zlk.java8.book;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * 在接口内对枚举进行子归类
 * @Date 2022/9/22 10:33
 */
public interface Food {

    /**
     * 开胃菜
     */
    enum Appetizer implements Food{
        SALAD, SOUP, SPRING_ROLLS;
    }

    /**
     * 主菜
     */
    enum MainCourse implements Food {
        LASAGNE, BURRITO, PAD_THAI,
        LENTILS, HUMMUS, VINDALOO;
    }

    /**
     * 甜点
     */
    enum Dessert implements Food {
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARAMEL;
    }

    /**
     * 咖啡
     */
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
        LATTE, CAPPUCCINO, TEA, HERB_TEA;
    }

}
