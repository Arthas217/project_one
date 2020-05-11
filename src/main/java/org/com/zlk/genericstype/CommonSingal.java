package org.com.zlk.genericstype;

import org.com.zlk.model.Base;
import org.com.zlk.model.Sub;

import java.util.Collection;

/**
 * 通配符
 * <?> 无限定的通配符。
 * <? extends T> 被称作有上限的通配符。
 * <? super T> 被称作有下限的通配符。
 */
public class CommonSingal {

    // Collection 对象丧失了 add() 方法的功能，编译器不通过。
    // 只保留与具体类型无关的功能,它只关心元素的数量、容器是否为空
    // 提高了代码的可读性，程序员看到这段代码时，就能够迅速对此建立极简洁的印象，能够快速推断源码作者的意图
    private static void wildList(Collection<?> collection) {
        collection.isEmpty();
        collection.iterator();
        collection.size();
    }

    //通配符能干的事情都可以用类型参数替换
    // 能够进行写操作 只不过要进行强制转换。
    private static <T> void wildList1(Collection<T> collection) {
        collection.add((T)new Integer(1));
    }

    /**
     * <? extends T> 代表类型 T 及 T 的子类。
     */
    public void testSub(Collection<? extends Base> para){
        //编译不通过
//        para.add(new Base());
//        para.add(new Sub());
    }


    /**
     * <? super Sub> 代表类型 T 及 T 的超类
     */
    public void testSuper(Collection<? super Sub> para){
        para.add(Sub.builder().value(1).num(1).build());
//        para.add(new Base());
    }



}
