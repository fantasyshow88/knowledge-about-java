package com.statestreet.demo.jvm.notes._07虚拟机类加载机制.classstatic;

public class Test {

	static int j = 3;
	
	static {
		i = 1;//可以赋值，但是被后面的2覆盖了，按照 static 出现的顺序
//		System.out.println(i);//不能访问(只能访问定义在此static 块之前的变量)
		System.out.println(j);//可以访问
	}
	//此顺序决定了 i 的值
	static int i = 2;
	
	public static void main(String[] args) {
		
		System.out.println(i);
		
	}
}
