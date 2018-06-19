package com.roocon.sharding04;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.roocon.mapper.AppMapper;

public class Main {
	
	private static ApplicationContext context;
	
	public static final int SHARDING_COUNT = 3;
	
	private static List<String> tables = new ArrayList<>();
	private static AppMapper mapper ;
	
	static {
		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		mapper = context.getBean(AppMapper.class);
		
		String sql = "show tables";
		List<Map<String, Object>> tas = mapper.executeQuery(sql);
		for(Map<String, Object> table : tas) {
			String t = (String) table.values().iterator().next();
			tables.add(t);
		}		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入id：");
		int id = sc.nextInt();
		
		System.out.println("雇员姓名：");
		String ename = sc.next();
		
		sc.close();
		
		int sharding = id % SHARDING_COUNT;  // 对分片字段取模运算，计划分多少个，那么 sharding_count 这个值就为多少
		String tableName = "mytable_" + sharding; 
		
		if(!tables.contains(tableName)) {
			String sql = "create table " + tableName + "(id int primary key,name varchar(10))";
			mapper.executeQuery(sql);
			tables.add(tableName);
		}
		
		String sql = "insert into " + tableName + " values ("+id+",'"+ename+"')";
		mapper.executeQuery(sql);
	}

}
