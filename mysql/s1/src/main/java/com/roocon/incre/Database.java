package com.roocon.incre;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.roocon.mapper.AppMapper;

public class Database {
	
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		
		AppMapper mapper = context.getBean(AppMapper.class);
		
		Bean b = new Bean();
		mapper.insert(b);
		
		System.out.println(b);
		
		
		
	}

}
