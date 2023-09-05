package org.com.zlk.chxg.spring.beanregister;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/9/5 18:18
 */
public class BeanRegisterTest {

    private static Logger  logger = LoggerFactory.getLogger(BeanRegisterTest.class);

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring-config.xml");
        IUserDao userDao = beanFactory.getBean("userDao", IUserDao.class);
        String res = userDao.queryUserInfo();
        logger.info("测试结果：{}", res);
    }
}
