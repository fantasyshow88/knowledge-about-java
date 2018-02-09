package com.statestreet.demo.java.thread.basic;

public class Singleton {

	private static volatile Singleton singleton = null;
	
	private Singleton() {}
	
	public static Singleton getInstance() {
		if(singleton == null) {
			synchronized(Singleton.class) {
				if(singleton == null) {
					singleton = new Singleton();
				}
				
			}
			
		}
		return singleton;
	}
}
