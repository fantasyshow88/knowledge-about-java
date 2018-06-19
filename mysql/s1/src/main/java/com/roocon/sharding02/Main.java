package com.roocon.sharding02;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.roocon.mapper.AppMapper;

public class Main {
	
	private static List<String> tables = new ArrayList<>();
	private static ClassPathXmlApplicationContext context ;
	private static AppMapper mapper ;
	
	private static int segment[] = new int[1024];
	
	static {
		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		mapper = context.getBean(AppMapper.class);
		
		String sql = "show tables";
		List<Map<String, Object>> tas = mapper.executeQuery(sql);
		for(Map<String, Object> table : tas) {
			String t = (String) table.values().iterator().next();
			tables.add(t);
		}
		
		for(int i = 0;i<512;i++) {
			segment [i] = 0;
		}
		
		for(int i = 512;i<768;i++) {
			segment [i] = 1;
		}
		
		for(int i = 768;i<896;i++) {
			segment [i] = 2;
		}
		
		for(int i = 896;i<1024;i++) {
			segment [i] = 3;
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入id：");
		int id = sc.nextInt();
		
		System.out.println("雇员姓名：");
		String ename = sc.next();
		
		sc.close();
		
		System.out.println("id :" + id + " name :" + ename);
		
		
		int hash = new Integer(id).hashCode();
		int sharding = hash&1024;
		// 判断是否存在表
		String tableName = "mytable_" + segment[sharding];
		if(!tables.contains(tableName)) {
			String sql = "create table " + tableName + "(id int primary key,name varchar(10))";
			mapper.executeQuery(sql);
			tables.add(tableName);
		}
		
		String sql = "insert into " + tableName + " values ("+id+",'"+ename+"')";
		mapper.executeQuery(sql);
		
		
		
		
		
	}

}
