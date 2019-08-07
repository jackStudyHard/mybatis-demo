package com.fc.dal.plugin;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Statement;
import java.util.Properties;

/**
 * @author lize
 * @date 6/23/19 10:11 PM
 */
@Intercepts({@Signature(type = StatementHandler.class,
	method = "query",
	args = {Statement.class, ResultHandler.class})})
public class TestPlugin implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		Statement mappedStatement = (Statement) args[0];
//		BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
//		System.out.println(String.format("plugin output sql = %s, param = %s", boundSql.getSql(), boundSql.getParameterObject()));
		System.out.println("============== coming in");
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
