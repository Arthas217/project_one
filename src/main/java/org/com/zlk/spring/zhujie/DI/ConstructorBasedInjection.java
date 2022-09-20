package org.com.zlk.spring.zhujie.DI;

import org.com.zlk.zhouyang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 基于构造函数的依赖注入
 * 主要优点是可以将需要注入的字段声明为final， 使得它们会在类实例化期间被初始化，这对于所需的依赖项很方便。
 * @Date 2022/9/19 08:45
 */
@Component
public class ConstructorBasedInjection {

    /**
     * 这里也可以是引用对象
     */
    private final User injectedBean;

    /**
     * 类构造函数被标注为@Autowired（可省略），并包含了许多与要注入的对象相关的参数。
     * @param injectedBean
     */
    @Autowired
    public ConstructorBasedInjection(User injectedBean) {
        this.injectedBean = injectedBean;
    }
}
