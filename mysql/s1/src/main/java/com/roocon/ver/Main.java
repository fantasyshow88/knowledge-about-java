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
public class Main {
	
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		AppMapper mapper = context.getBean(AppMapper.class);
		
		// 查询 所有的雇员信息
		
		DbContextHolder.set(DbContextHolder.SLAVE);
		String sql = "select ename,deptno from emp";
		List<Map<String, Object>> emps = mapper.executeQuery(sql);
		
		
		//------------------------------------------
		DbContextHolder.set(DbContextHolder.MASTER);
		for(Map<String, Object> emp : emps) {
			Integer deptno = (Integer) emp.get("deptno");
			String dsql = "select dname from dept where deptno = " + deptno;
			String dname = mapper.executeQueryDname(dsql);
			emp.put("dname", dname);
		}
		
		System.out.println(emps);
		
	}
	

}
