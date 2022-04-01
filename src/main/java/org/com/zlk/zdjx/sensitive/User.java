package org.com.zlk.zdjx.sensitive;

import java.io.Serializable;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/3/22 12:48
 */
public class User implements Serializable {

    private static final long serialVersionUID = 3870447235066629073L;

    @Sensitive(type = SensitiveTypeEnum.CHINESE_NAME, prefixNoMaskLen = 1, suffixNoMaskLen = 1,maskStr = "$")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
