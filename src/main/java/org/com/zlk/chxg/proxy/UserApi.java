package org.com.zlk.chxg.proxy;

/**
 * @author 会游泳的蚂蚁
 * @description: 接口实现类
 * @date 2023/8/31 23:52
 */
public class UserApi implements IUserApi{
    @Override
    public String queryUserInfo() {
        return "小傅哥，公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！";
    }
}
