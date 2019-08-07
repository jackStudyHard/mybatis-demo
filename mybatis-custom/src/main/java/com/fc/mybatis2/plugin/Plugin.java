package com.fc.mybatis2.plugin;

import com.fc.mybatis2.annotation.Signature;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lize
 * @date 7/6/19 8:02 PM
 */
public class Plugin implements InvocationHandler {
	private Object target;
	private Interceptor interceptor;
	private Map<Class<?>, Set<Method>> signatureMap;

	public Plugin(Object target, Interceptor interceptor, Map<Class<?>, Set<Method>> signatureMap) throws NoSuchMethodException {
		this.target = target;
		this.interceptor = interceptor;
		this.signatureMap = signatureMap;
	}

	public static Object wrap(Object target, Interceptor interceptor) throws NoSuchMethodException {
		// 决定是否植入
		if (interceptor.getClass().isAnnotationPresent(com.fc.mybatis2.annotation.Interceptor.class)) {
			Map<Class<?>, Set<Method>> signatureMap = getAnnotatedMethod(interceptor);
			if (signatureMap.keySet().stream().anyMatch(o -> o.isAssignableFrom(target.getClass()))) {
				return Proxy.newProxyInstance(target.getClass().getClassLoader(),
						target.getClass().getInterfaces(), new Plugin(target, interceptor, signatureMap));
			}
		}
		return target;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Set<Method> methods = signatureMap.get(method.getDeclaringClass());
		if (!methods.contains(method)) { // 不在拦截的范围内
			return method.invoke(target, args);
		}
		// 拦截
		return interceptor.intercept(target, method, args);
	}

	private static Map<Class<?>, Set<Method>> getAnnotatedMethod(Interceptor interceptor) throws NoSuchMethodException {
		Map<Class<?>, Set<Method>> result = new HashMap<>();
		com.fc.mybatis2.annotation.Interceptor annotation = interceptor.getClass()
				.getDeclaredAnnotation(com.fc.mybatis2.annotation.Interceptor.class);
		Signature[] signatures = annotation.value();
		for (Signature signature : signatures) {
			Set<Method> set = result.computeIfAbsent(signature.type(), o -> new HashSet<Method>());
			if (set == null) {
				set = result.get(signature.type());
			}
			Method method = signature.type().getMethod(signature.methodName(), signature.args());
			set.add(method);
		}

		return result;
	}

}
