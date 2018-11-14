package com.itmuch.cloud.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor{

	public MyBeanPostProcessor() {
		System.out.println("===================== BeanPostProcessor 构造方法");
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("=================BeanPostProcessor  postProcessBeforeInitialization -->bean:" +bean+ ", beanName:" + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("== BeanPostProcessor  postProcessAfterInitialization -->bean:" +bean+ ", beanName:" + beanName);
		return bean;
	}

}
