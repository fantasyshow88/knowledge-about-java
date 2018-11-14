package com.itmuch.cloud.spring;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter{

	public MyInstantiationAwareBeanPostProcessor() {
		System.out.println("================ InstantiationAwareBeanPostProcessorAdapter 构造器");
	}
	
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println("================= InstantiationAwareBeanPostProcessorAdapter 的 postProcessBeforeInstantiation方法-->beanClass:" + beanClass + ", beanName: " + beanName);
		return null;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("===================== InstantiationAwareBeanPostProcessorAdapter 的 postProcessAfterInitialization方法-->bean:" + bean + ", beanName: " + beanName);
		return bean;
	}
	
	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean,
			String beanName) throws BeansException {
		 System.out.println("== InstantiationAwareBeanPostProcessorAdapter调用 postProcessPropertyValues方法");
		return pvs;
	}
	
}
