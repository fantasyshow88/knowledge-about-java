package com.itmuch.cloud.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Person implements BeanNameAware,BeanFactoryAware,InitializingBean,DisposableBean{
	
	private String name;
	
	private String address;
	
	private int phone;
	
	private String beanName;
	
	private BeanFactory beanFactory;
	
	
	
	public Person() {
		System.out.println("================【构造器】调用Person的构造器实例化");
	}



	@Override
	public void setBeanName(String name) {
		 System.out.println("===============【BeanNameAware接口】调用 BeanNameAware.setBeanName()");
		this.beanName = name;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		System.out.println("===================【注入属性 : setName】注入属性 name:" + name);
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		System.out.println("======================【注入属性 setAddress 】注入属性address：" + address);
		this.address = address;
	}



	public int getPhone() {
		return phone;
	}



	public void setPhone(int phone) {
		System.out.println("=========================【注入属性 setPhone】注入属性phone：："+ phone);
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "================== Person [address=" + address + ", name=" + name + ", phone=" + phone + "]";
	}



	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("======================【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
		this.beanFactory = beanFactory;
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("===========================【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
	}
	
	public void myInit() {
		System.out.println("============================【init-method】调用<bean>的init-method属性指定的初始化方法");
	}
	
	public void myDestory() {
		System.out.println("=================================【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
	}



	@Override
	public void destroy() throws Exception {
		System.out.println("================================= 【DiposibleBean接口】调用DiposibleBean.destory()");
		
	}
	
	
}
