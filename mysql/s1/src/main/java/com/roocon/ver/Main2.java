package com.roocon.ver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.roocon.mapper.AppMapper;
import com.roocon.multi.DbContextHolder;

/**
 * 查询 所有雇员及所在部门的名称
 * select e.ename,d.dname from emp e,dept d where e.deptno = d.deptno
 * 
 * 
 * select e.ename,e.depnto from emp;
 * 
 * 
 * @author worker
 *
 */
public class Main2 {
	
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		AppMapper mapper = context.getBean(AppMapper.class);
		
		// 查询 所有的雇员信息
		
		DbContextHolder.set(DbContextHolder.SLAVE);
		String sql = "select e.ename,d.dname from emp e,dept d where e.deptno = d.deptno";
		List<Map<String, Object>> emps = mapper.executeQuery(sql);
		
		System.out.println(emps);
	}
	

}
