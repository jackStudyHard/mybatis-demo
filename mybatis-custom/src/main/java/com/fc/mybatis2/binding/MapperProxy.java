package com.fc.mybatis2.binding;

import com.fc.mybatis2.session.Configuration;
import com.fc.mybatis2.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lize
 * @date 7/6/19 9:27 AM
 */
public class MapperProxy implements InvocationHandler {

	private final SqlSession sqlSession;
	private final Class pojo;

	public MapperProxy(SqlSession sqlSession, Class pojo) {
		this.sqlSession = sqlSession;
		this.pojo = pojo;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String className = method.getDeclaringClass().getName();
		String methodName = method.getName();
		String sql = sqlSession.getConfiguration().getMappedStatement(className + "." + methodName);

		return sqlSession.select(pojo, sql, args);
	}

	public static void main(String[] args) {
		System.out.println(MapperProxy.class.getName());
	}
}
