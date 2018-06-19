package com.roocon.sharding01;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.roocon.mapper.AppMapper;

public class Main extends Object {
	
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	private static ClassPathXmlApplicationContext context;
	
	private static Map<Integer, String> mapping = new HashMap<>();
	
	private static Properties prop = new Properties();
	
	static {
		
		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		mapping.put(0, "emp_0");
		mapping.put(1, "emp_1");
		
		try {
			prop.load(Main.class.getResourceAsStream("rule.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		Enumeration<Object> e = prop.keys();
//		
//		while(e.hasMoreElements()) {
//			String key = (String) e.nextElement();
//			String value = prop.getProperty(key);
//			System.out.println("key : " + key  + " value : " + value + " table : " + mapping.get(Integer.parseInt(value)));
//		}
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入id：");
		int id = sc.nextInt();
		
		System.out.println("雇员姓名：");
		String ename = sc.next();
		
		System.out.println("分片字段：");
		int deptno = sc.nextInt();
		
		sc.close();
		
		Emp emp = new Emp();
		emp.setEname(ename);
		emp.setEmpno(id);
		emp.setDeptno(deptno);
		System.out.println("用户输入的信息为：" + emp);
		System.out.println("------------------------");
		
		
		// 获取appMapper
		AppMapper mapper = context.getBean(AppMapper.class);
		
		
		String m = prop.getProperty(deptno+"");
		if(m == null || m.trim().equals("")) {
			throw new RuntimeException("未知的枚举规则");
		}
		String tableName = mapping.get(Integer.parseInt(m));
		if(logger.isInfoEnabled()) {
			logger.info("路由到的表名为：" + tableName);
		}
		// 验证是否存在
		
		// 插入数据
		String sql = "insert into " + tableName + " (empno,ename,deptno) values ("+id+",'"+ename+"',"+deptno+")";
		
		mapper.executeQuery(sql);
		
	}
	
	
	

}
