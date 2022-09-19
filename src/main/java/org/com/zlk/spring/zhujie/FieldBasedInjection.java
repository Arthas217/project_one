package org.com.zlk.spring.zhujie;

import org.com.zlk.zhouyang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 基于属性的依赖注入
 * 字段/属性被标注为@Autowired。一旦类被实例化，Spring容器将设置这些字段。
 * @Date 2022/9/19 08:53
 */
@Component
public class FieldBasedInjection {

    /**
     * 它避免了添加样板代码，并且不需要声明类的构造函数。代码看起来很干净简洁
     * 但是正如代码检查器已经向我们暗示的那样，这种方法有一些缺点。
     * 1、不允许声明不可变域
     * 基于字段的依赖注入在声明为 final / immutable 的字段上不起作用，因为这些字段必须在类实例化时实例化。
     * 声明不可变依赖项的惟一方法是使用基于构造器的依赖注入。
     * 2 容易违反单一职责设计原则
     * 随着时间的推移，类中逐渐添加越来越多的依赖项，很容易忽略类中的依赖已经太多了，隐藏了信号，使我们很容易忽略这些信号。
     * 3 与依赖注入容器紧密耦合
     * 不使用getter和setter的样板代码或为类创建构造函数，基于属性依赖注入设置这些字段的唯一方法是通过Spring容器实例化类并使用反射注入它们，否则字段将保持null。
     * 会因为再次与类注入容器(在本例中是Spring)耦合而丢失，从而使类在Spring容器之外变得无用。
     * 想在应用程序容器之外使用您的类，例如用于单元测试，您将被迫使用Spring容器来实例化您的类，因为没有其他可能的方法(除了反射)来设置自动装配字段。
     * 4 隐藏依赖关系
     * 在使用依赖注入时，受影响的类应该使用公共接口清楚地公开这些依赖项
     * 在构造函数中公开所需的依赖项，或者使用方法(setter)公开可选的依赖项。
     * 当使用基于字段的依赖注入时，实质上是将这些依赖对外隐藏了。
     */
    @Autowired
    private User injectedBean;


}
