package com.statestreet.demo.java.thread.basic.interview;

public class Test3 {

	public static void main(String[] args) {
		String a = "1";
		String b = getString("","1");
		String c = getString("","1");
		System.out.println(a == b);
		System.out.println(c == b);
		
	}
	
	public static String getString(String a,String b) {
		return a + b;
	}
	
}
