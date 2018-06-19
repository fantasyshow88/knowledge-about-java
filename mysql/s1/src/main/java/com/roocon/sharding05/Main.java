package com.roocon.sharding05;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.roocon.mapper.AppMapper;

public class Main {
	
	private static ApplicationContext context;
	
	public static final int SHARDING_DAY = 3;
	
	private static final String beginDate = "2017-08-01";
	
	private static final String formatStr = "yyyy-MM-dd";
	
	private static SimpleDateFormat format ;
	
	private static List<String> tables = new ArrayList<>();
	private static AppMapper mapper ;
	
	static {
		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		mapper = context.getBean(AppMapper.class);
		format = new SimpleDateFormat(formatStr);
		
		String sql = "show tables";
		List<Map<String, Object>> tas = mapper.executeQuery(sql);
		for(Map<String, Object> table : tas) {
			String t = (String) table.values().iterator().next();
			tables.add(t);
		}		
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入id：");
		int id = sc.nextInt();
		
		System.out.println("雇员姓名：");
		String date = sc.next();
		
		sc.close();
		
		long begin = format.parse(beginDate).getTime();
		long end = format.parse(date).getTime();
		
		long sharding = (end - begin) / (24 * 60 * 60 * 1000) / SHARDING_DAY;
		
		String tableName = "mytable_" + sharding;
		
		if(!tables.contains(tableName)) {
			String sql = "create table " + tableName + "(id int primary key,name varchar(10))";
			mapper.executeQuery(sql);
			tables.add(tableName);
		}
		
		// 14/3 = 4
		
		String sql = "insert into " + tableName + " values ("+id+",'"+date+"')";
		mapper.executeQuery(sql);
		
	}
	

}
