package org.com.zlk.zdjx.sensitive;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 对象脱敏注解
 * https://blog.csdn.net/qq_28369007/article/details/119006766?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~aggregatepage~first_rank_ecpm_v1~rank_v31_ecpm-16-119006766.pc_agg_new_rank&utm_term=%E5%A7%93%E5%90%8D%E8%84%B1%E6%95%8F%E8%A7%84%E5%88%99&spm=1000.2123.3001.4430
 * @Date 2022/3/22 12:23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
//@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerialize.class)
public @interface Sensitive {

    /**
     * 脱敏数据类型, 非Customer时, 将忽略refixNoMaskLen和suffixNoMaskLen和maskStr
     */
    SensitiveTypeEnum type() default SensitiveTypeEnum.CUSTOMER;

    /**
     * 前置不需要打码的长度
     */
    int prefixNoMaskLen() default 0;

    /**
     * 后置不需要打码的长度
     */
    int suffixNoMaskLen() default 0;

    /**
     * 打码方式
     */
    String maskStr() default "*";
}
