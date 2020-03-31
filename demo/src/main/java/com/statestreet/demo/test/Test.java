package com.statestreet.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args) {
		String str2 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str2.intern() == str2);
	
		
		String str1 = new StringBuilder("ja").append("va").toString();
		System.out.println(str1.intern() == str1);
		
		int count = 32;
		count += count++;
		System.out.println(count);

		System.out.println("ni好".length());
		
		List<String> list = new ArrayList<String>();
		for (String string : list) {
			
		}
		
//		Set<Entry<Thread, StackTraceElement[]>> a = Thread.getAllStackTraces().entrySet();
		System.out.println("---------------------------------------------------------------");
		for(Entry<Thread, StackTraceElement[]> stackTrace:Thread.getAllStackTraces().entrySet() ) {
			Thread t = stackTrace.getKey();
			StackTraceElement[] stack = stackTrace.getValue();
			
			System.out.println("线程" + t.getName());
			for (StackTraceElement stackTraceElement : stack) {
				System.out.println(stackTraceElement);
			}
		}
		
		
	}
}
