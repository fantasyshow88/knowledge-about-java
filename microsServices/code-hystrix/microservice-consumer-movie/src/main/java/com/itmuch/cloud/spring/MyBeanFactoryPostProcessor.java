package com.itmuch.cloud.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	public MyBeanFactoryPostProcessor() {
		System.out.println("=============== BeanFactoryPostProcessor 构造函数 ");
	}
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("============ BeanFactoryPostProcessor 调用postProcessBeanFactory方法");
		BeanDefinition bd = beanFactory.getBeanDefinition("person");
		bd.getPropertyValues().addPropertyValue("phone", 111);
	}

}
