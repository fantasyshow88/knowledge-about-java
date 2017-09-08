package com.statestreet.demo.jvm.notes._07虚拟机类加载机制.classinit;

public class SubClass extends SuperClass {

	static {
		System.out.println("subClass init!");
	}
	
}
