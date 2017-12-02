package com.statestreet.demo.java.java8.notes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import com.statestreet.demo.java.java8.day01.Employee;


public class TestLambda {

	public static void main(String[] args) {
		Comparator<Integer> c = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		};
		
		Comparator<Integer> c2 = (x,y)-> {
			return Integer.compare(x, y);
			
		};
		
		Comparator<Integer> c3 = (x,y)-> Integer.compare(x, y);
			
		
		Comparator<Integer> c4 = new Comparator<Integer>() {
			
			
			@Override
			public boolean equals(Object obj) {
				// TODO Auto-generated method stub
				return super.equals(obj);
			}

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		/////////
		
		List<Employee> emps = Arrays.asList(new Employee("aa",11),new Employee("bb",22));
		emps.stream()
		.filter((e)->e.getAge() >1)
		.forEach(System.out::println);;
		
		/////////
		String[] s = {"a"};
/*		String[] s2;
		s2 = {"a"};*/
		
		///
		List<Employee> list = Arrays.asList(
				new Employee(101, "张三", 18, 9999.99),
				new Employee(102, "李四", 59, 6666.66),
				new Employee(103, "王五", 28, 3333.33),
				new Employee(104, "赵六", 8, 7777.77),
				new Employee(105, "田七", 38, 5555.55)
		);
		Collections.sort(list,(x,y)->x.getAge()- y.getAge());
		System.out.println(list);
	}

	@Test
	public void test() {
		System.out.println(strHandler("  aa  ", new MyHandler() {
			@Override
			public String getValue(String str) {
				return str.trim();
			}
		}));
		
		System.out.println(strHandler("  aa  ", (str)->str.trim()));
	}
	
	
	public String strHandler(String str, MyHandler handler) {
		return handler.getValue(str);
	}
	
	//四大核心函数式接口
	
	
}
