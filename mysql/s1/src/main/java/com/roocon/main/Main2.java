package com.roocon.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.roocon.mapper.AppMapper;

public class Main2 {
	
	// show tables 
	// create table mytable_? (id int primary key,name varchar(10));
	// insert into mytable_? (id , name) values (?,?)
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入id：");
		Integer id = sc.nextInt();
		System.out.println("请输入名称：");
		String name  = sc.next();
		
		sc.close();
		System.out.println("接受到的id为：" + id + " name 为：" + name);
		
		
		if(id == null || name == null) {
			System.out.println("参数有误！");
			System.exit(0);
		}
		
		/**
		 * 1-100   mytable_1
		 * 	a  mytable_1_a
		 *  b  mytable_1_b
		 * 100-200 mytable_2
		 * 	a	mytable_2_a
		 * 200-300 mytable_3
		 * ...
		 * 
		 */
		
		
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		AppMapper mapper = ac.getBean(AppMapper.class);
		
		String selectTableSql = "show tables";
		List<Map<String, Object>> allTable = mapper.executeQuery(selectTableSql);
		
		// 所有表的名称
		List<String> tableNames = new ArrayList<>();
		for(Map<String, Object> table : allTable) {
			tableNames.add((String)table.get("Tables_in_mytest"));
		}
//		
//		List<List<Map<String, Object>>> res = new ArrayList<>();
//		for(String tableName : tableNames) {
//			if(tableName!= null && tableName.startsWith("mytable")) {
//				String sql = "select * from " + tableName + " where name = 'hello'";
//				List<Map<String, Object>> values = mapper.executeQuery(sql);
//				res.add(values);
//			}
//		}
		
//		System.out.println(res);
		
//		char firstChar = name.charAt(0);
//		String t = "mytable_" + firstChar;
//		if(!tableNames.contains(t)) {
//			mapper.executeQuery("create table "+t+" (id int primary key,name varchar(10));");
//		}
//		String insertSql = "insert into "+t+" (id , name) values ("+id+",'"+name+"')";
//		mapper.executeQuery(insertSql);
		
		String t  = "mytable_" + name.charAt(0);
		String sql = "select * from " + t + " where name = '" + name + "'";
		System.out.println(mapper.executeQuery(sql));
		
		
		
		ac.close();
	}

}
