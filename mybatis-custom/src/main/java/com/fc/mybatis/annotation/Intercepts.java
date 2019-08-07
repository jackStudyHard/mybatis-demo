package com.fc.mybatis.annotation;

import java.lang.annotation.*;

/**
 * @author lize
 * @date 6/23/19 9:56 PM
 * 用于注解拦截器，指定拦截的方法
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Intercepts {
    String value();
}
