package com.statestreet.demo.test;

public class Person {

	String name;
	int age;
	
	public Person(String name,int age) {
		this.name = name;
		this.age = age;
	}
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	@Override
	public String toString() {
		return "name: " + name + " , age : " + age;
	}
	public static void main(String[] args) {
		Person p = new Person("aaa",11);
		
		Person p1 = p;
		System.out.println(p1);
//		p = new Person("bb",22);
		p.age = 22334;
		System.out.println(p1);	
		System.out.println(p);		
	}
}
