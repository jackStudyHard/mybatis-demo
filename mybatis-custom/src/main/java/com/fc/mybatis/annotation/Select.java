package com.fc.mybatis.annotation;

import java.lang.annotation.*;

/**
 * @author lize
 * @date 6/23/19 9:48 PM
 * 注解方法，配置SQL语句
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Select {
    String value();
}
