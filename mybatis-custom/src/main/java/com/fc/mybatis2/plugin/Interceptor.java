package com.fc.mybatis2.plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lize
 * @date 7/6/19 8:03 PM
 */
public interface Interceptor {

	Object plugin(Object target) throws NoSuchMethodException;

	Object intercept(Object target, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
}
