package org.com.zlk.java8.book;

import com.google.common.base.Enums;

import java.security.Security;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/22 10:47
 */
public enum SecurityCategory {

    STOCK(Security.Stock.class),
    BOND(Security.Bond.class);
    Security[] values;
    SecurityCategory(Class<? extends Security> kind) {
        values = kind.getEnumConstants();
    }

    interface Security {
        enum Stock implements Security {
            SHORT, LONG, MARGIN
        }
        enum Bond implements Security {
            MUNICIPAL, JUNK
        }
    }
    public Security randomSelection() {
        return EnumUtil.random(values);
    }
}
