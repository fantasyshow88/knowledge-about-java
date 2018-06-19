package com.roocon.sharding03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.roocon.mapper.AppMapper;

public class Main {
	
	private static ApplicationContext context;
	
	private static List<String> tables = new ArrayList<>();
	private static AppMapper mapper ;
	
	private static Map<Integer, Bean> mapping = new HashMap<>();
	
	static {
		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		mapper = context.getBean(AppMapper.class);
		
		String sql = "show tables";
		List<Map<String, Object>> tas = mapper.executeQuery(sql);
		for(Map<String, Object> table : tas) {
			String t = (String) table.values().iterator().next();
			tables.add(t);
		}		
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("rule.conf")));
		String line = null;
		try {
			while((line = reader.readLine()) != null) {
				String [] s = line.split(" ");
				String [] ss = s[0].split("\\-");
				Bean bean = new Bean();
				bean.setMin(Integer.parseInt(ss[0]));
				bean.setMax(Integer.parseInt(ss[1]));
				bean.setSharding(Integer.parseInt(s[1]));
				
				mapping.put(bean.getSharding(), bean);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入id：");
		int id = sc.nextInt();
		
		System.out.println("雇员姓名：");
		String ename = sc.next();
		
		sc.close();
		
		Collection<Bean> values = mapping.values();
		for(Bean bean : values) {
			if(id >= bean.getMin() && id < bean.getMax()) {
				Integer sharding = bean.getSharding();
				
				// 判断是否存在表
				String tableName = "mytable_" + sharding;
				if(!tables.contains(tableName)) {
					String sql = "create table " + tableName + "(id int primary key,name varchar(10))";
					mapper.executeQuery(sql);
					tables.add(tableName);
				}
				
				String sql = "insert into " + tableName + " values ("+id+",'"+ename+"')";
				mapper.executeQuery(sql);
				
				break;
			}
		}
		
	}
	
}
