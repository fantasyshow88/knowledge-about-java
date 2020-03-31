package com.statestreet.demo.jvm.notes._07虚拟机类加载机制.classinit;

public class Test {
	
	//VM args:-XX:+TraceClassLoading 可以观察类的加载过程
	public static void main(String[] args) {
		//1.只触发该静态字段定义的类的初始化
		System.out.println(SubClass.value);
		
		System.out.println("------------------");
		//2.通过数组定义不会初始化类
		SuperClass [] sca = new SuperClass[10];
		
		System.out.println("------------------");
		
		//3 引用产量时不会初始化类
		System.out.println(SuperClass.CONSTANT);
		
	}
}
