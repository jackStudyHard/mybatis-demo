package com.fc.mybatis2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lize
 * @date 7/6/19 8:59 AM
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Signature {
	Class<?> type();
	String methodName();
	Class<?>[] args();
}
