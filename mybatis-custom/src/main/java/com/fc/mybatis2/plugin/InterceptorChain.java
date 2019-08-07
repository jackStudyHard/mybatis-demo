package com.fc.mybatis2.plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lize
 * @date 7/6/19 7:55 PM
 */
public class InterceptorChain {
	private List<Interceptor> interceptors = new ArrayList<>(16);

	public Object pluginAll(Object target)throws Exception  {
		for (Interceptor interceptor : interceptors) {
			target = interceptor.plugin(target);
		}
		return target;
	}

	public void addInterceptors(Interceptor interceptor) {
		interceptors.add(interceptor);
	}
}
