package org.com.zlk.collection;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/7/27 15:11
 */
public enum CardHighLevelEnum {

    BFCHJK(1, "百夫长黑金卡"),
    YLHJK(2, "银联黑金卡"),
    ZSK(3, "钻石卡"),
    YTBJK(4, "工银运通铂金卡"),
    BJK(5, "白金卡"),
    JK(6, "金卡"),
    PK(7, "银联黑金卡");

    CardHighLevelEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    private final Integer key;
    private final String name;

    public Integer getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public static String getCardLevelName(Integer key){
        for(CardHighLevelEnum cardHighLevelEnum: CardHighLevelEnum.values()){
            if(cardHighLevelEnum.getKey().equals(key)){
                return cardHighLevelEnum.getName();
            }
        }
        return "";
    }
}
