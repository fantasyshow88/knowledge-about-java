package com.hy.db;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class DataSourceAspect {
	
	public void before(JoinPoint point) {
		MethodSignature s = (MethodSignature) point.getSignature();
		Method method = s.getMethod();
		
		DataSource ds = method.getAnnotation(DataSource.class);
		if(ds == null) {
			return;
		}
		
		String value = ds.value();
		System.out.println(value);
		// 设置要使用的数据源
		DbContextHolder.set(value);
	}

}
