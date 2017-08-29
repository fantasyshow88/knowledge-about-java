package com.statestreet.demo.test;

public class Test {

	public static void main(String[] args) {
		String str2 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str2.intern() == str2);
	
		
		String str1 = new StringBuilder("ja").append("va").toString();
		System.out.println(str1.intern() == str1);

	}
}
