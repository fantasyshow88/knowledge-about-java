package com.statestreet.demo.java.java8.notes;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

import com.statestreet.demo.java.java8.day01.Employee;

public class TestMethodRef {

	//对象::实例方法名(lambda的另外一种表达形式)
	@Test
	public void test1() {
		//1
		Consumer<String> con = (x)->System.out.println(x);
		//2
		Consumer<String> conn2 = System.out::println;
		//3
		PrintStream ps = System.out;
		Consumer<String> conn3 = ps::println;
		//void accept(T t) 和 public void println(String x) {...} 的参数和返回值一致，可以用方法引用表示
		//即 1 和 2 是一样的
		conn3.accept("asd");
	}
	
	//对象::实例方法名(lambda的另外一种表达形式)
	@Test
	public void test2() {
		Employee e = new Employee();
		e.setAge(11);
		e.setName("aa");
		
		Supplier<String> s1 = ()->e.getName();
		
		Supplier<Integer> s2 = e::getAge;
		
		
		System.out.println(s1.get());
		System.out.println(s2.get());
		
	}
	
	
	//类::静态方法
	@Test
	public void test3() {
		Comparator<Integer> c = (x,y)->Integer.compare(x, y);
		
		Comparator<Integer> c2 = Integer::compare;
	}
	
	//类::实例方法
	@Test
	public void test4() {
		BiPredicate<String, String> b = (x,y)->x.equals(y);
		
		BiPredicate<String, String> c = String::equals;
	}
	//构造器引用
	@Test
	public void test5() {
		Supplier<Employee> s = ()->new Employee();
		
		Supplier<Employee> s2 = Employee::new;
	}
	
	//数组引用
	@Test
	public void test6() {
		
		Function<Integer, String[]> f = (x)->new String[x];
		
		Function<Integer, String[]> f2 = String[]:: new;
		
	}
}
