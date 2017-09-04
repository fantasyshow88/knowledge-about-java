package com.statestreet.demo.jvm.notes._06class结构;

public class TestClass {
	private int m;
	public int incO() {
		return m + 1;
	}
}


/*

javac TestClass.java //编译

javap -verbose TestClass //查看类结构

*/