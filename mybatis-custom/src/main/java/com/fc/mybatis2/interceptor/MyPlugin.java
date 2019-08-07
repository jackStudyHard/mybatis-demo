package com.fc.mybatis2.interceptor;

import com.fc.mybatis.annotation.Intercepts;
import com.fc.mybatis.plugin.Interceptor;
import com.fc.mybatis.plugin.Invocation;
import com.fc.mybatis.plugin.Plugin;
import com.fc.mybatis2.annotation.Signature;
import com.fc.mybatis2.executor.Executor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lize
 * @date 6/23/19 9:46 PM
 * 自定义插件
 */
@com.fc.mybatis2.annotation.Interceptor(
        {@Signature(type = Executor.class, methodName = "query", args = {Class.class, String.class, Object[].class})})
public class MyPlugin implements com.fc.mybatis2.plugin.Interceptor {
    @Override
    public Object plugin(Object target) throws NoSuchMethodException {
        return com.fc.mybatis2.plugin.Plugin.wrap(target, this);
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("============ 拦截");
        return method.invoke(target, args);
    }
}
