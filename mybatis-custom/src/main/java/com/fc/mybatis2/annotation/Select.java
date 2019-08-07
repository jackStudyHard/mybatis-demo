package com.fc.mybatis2.annotation;

import java.lang.annotation.*;

/**
 * @author lize
 * @date 7/6/19 8:54 AM
 */
@Target(ElementType.METHOD)
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Select {
	String value();
}
