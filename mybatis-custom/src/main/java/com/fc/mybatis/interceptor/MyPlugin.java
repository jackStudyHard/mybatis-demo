package com.fc.mybatis.interceptor;

import com.fc.mybatis.annotation.Intercepts;
import com.fc.mybatis.plugin.Interceptor;
import com.fc.mybatis.plugin.Invocation;
import com.fc.mybatis.plugin.Plugin;

import java.util.Arrays;

/**
 * @author lize
 * @date 6/23/19 9:46 PM
 * 自定义插件
 */
@Intercepts("query")
public class MyPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String statement = (String) invocation.getArgs()[0];
        Object[] parameter = (Object[]) invocation.getArgs()[1];
        Class pojo = (Class) invocation.getArgs()[2];
        System.out.println("插件输出：SQL：["+statement+"]");
        System.out.println("插件输出：Parameters："+Arrays.toString(parameter));

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
