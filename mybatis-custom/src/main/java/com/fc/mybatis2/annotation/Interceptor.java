package com.fc.mybatis2.annotation;

import java.lang.annotation.*;

/**
 * @author lize
 * @date 7/6/19 8:56 AM
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Interceptor {
	Signature[] value();
}
