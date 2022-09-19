package org.com.zlk.spring.zhujie;

import org.com.zlk.zhouyang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 会游泳的蚂蚁
 * @Description:基于Setter的依赖注入
 * 一旦使用无参数构造函数或无参数静态工厂方法实例化Bean，为了注入Bean的依赖项，Spring容器将调用这些setter方法。
 * @Date 2022/9/19 08:50
 */
@Component
public class SetterBasedInjection {

    private User injectedBean;

    /**
     * @Autowired可以省略
     * @param injectedBean
     */
    @Autowired
    public void setInjectedBean(User injectedBean) {
        this.injectedBean = injectedBean;
    }
}
