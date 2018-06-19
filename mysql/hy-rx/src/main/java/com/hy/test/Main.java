package com.hy.test;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hy.mapper.AppMapper;

public class Main {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		AppMapper mapper = context.getBean(AppMapper.class);
		
		Calendar c = Calendar.getInstance();
		int currentYear = c.get(Calendar.YEAR);
		
		String sql ="select * from t_archievement_" + currentYear;
		
		List<Map<String, Object>> result = mapper.executeQuery(sql);
		
		System.out.println(result);
		
		context.close();
	}

}
