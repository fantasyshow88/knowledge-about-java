package com.statestreet.demo.jvm.notes._07虚拟机类加载机制.classinit;

public class SuperClass {

	static {
		System.out.println("Super Class init!");
	}
	
	public static int value = 1;
	
	public static final String CONSTANT = "Are you OK?";
}
