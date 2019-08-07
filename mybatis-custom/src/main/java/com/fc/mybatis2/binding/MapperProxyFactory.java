package com.fc.mybatis2.binding;

import com.fc.mybatis2.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * @author lize
 * @date 7/6/19 9:29 AM
 */
public class MapperProxyFactory<T> {
	private final Class<T> mapperInterface;
	private final Class pojo;

	// 用于创建mapperProxy
	public MapperProxyFactory(Class<T> mapperInterface, Class pojo) {
		this.mapperInterface = mapperInterface;
		this.pojo = pojo;
	}

	public T newInstance(SqlSession session) {
		return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] {mapperInterface},
				new MapperProxy(session, pojo));
	}
}
