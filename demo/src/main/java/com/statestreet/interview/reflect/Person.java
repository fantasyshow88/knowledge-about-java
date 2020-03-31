package com.statestreet.interview.reflect;

import java.lang.reflect.Field;

public class Person {

	private String name;
	
	private int age;
	
	
	public Person(String name,int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return name + " : " + age;
	}
	
	public static void main(String[] args) throws Exception {
		Person p = new Person("zs",12);
		System.out.println(p);
		//use reflect to set name and age 
		Field f = p.getClass().getDeclaredField("name");
		f.set(p, "lisi");
		Field ageFiled = p.getClass().getDeclaredField("age");
		ageFiled.set(p, 20);
		System.out.println(p);
	}
	
	
}
