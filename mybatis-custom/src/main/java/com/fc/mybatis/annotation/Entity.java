package com.fc.mybatis.annotation;


import java.lang.annotation.*;

/**
 * @author lize
 * @date 6/23/19 9:46 PM
 * 用于注解接口，以映射返回的实体类
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Entity {
    Class<?> value();
}
