package org.com.zlk.chxg.spring.beanregister;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author 会游泳的蚂蚁
 * @description: Bean对象注册， FactoryBean实现类注册到Spring
 * @date 2023/9/5 18:06
 */
public class RegisterBeanFactory implements BeanDefinitionRegistryPostProcessor {
    /**
     * 获取Bean注册对象
     * Spring的Bean管理中，所有的Bean最终都会被注册到类 DefaultListableBeanFactory 中，
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //Bean定义,主要设置了我们的代理类工厂
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(ProxyBeanFactory.class);
        //创建Bean定义处理类, 主要是Bean定义和名称
        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(beanDefinition, "userDao");
        //将自己bean注册到spring容器中去
        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
