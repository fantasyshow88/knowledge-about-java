package com.roocon.multi;

public class DbContextHolder {
	
	public static final String MASTER = "master";
	
	public static final String SLAVE  = "slave";
	
	private static ThreadLocal<String> dataSource = new ThreadLocal<>();
	
	public static void set(String name) {
		dataSource.set(name);
	}
	
	public static String get() {
		return dataSource.get();
	}
	

}
