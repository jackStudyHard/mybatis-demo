package com.fc.mybatis2.annotation;

import java.lang.annotation.*;

/**
 * @author lize
 * @date 7/6/19 8:52 AM
 */
@Target(ElementType.TYPE)
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {
	Class<?> value();
}
