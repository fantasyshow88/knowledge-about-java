package com.hy.db;

public class DbContextHolder {
	
	public static final String MASTER = "master";
	
	public static final String SLAVE  = "slave";
	
	private static ThreadLocal<String> dataSource = new ThreadLocal<String>();
	
	public static void set(String name) {
		dataSource.set(name);
	}
	
	public static String get() {
		return dataSource.get();
	}
	

}
