package com.statestreet.demo.java.thread.basic;

public class Singleton {

	private static Singleton singleton = null;
	
	private Singleton() {}
	
	public static Singleton getInstance() {
		if(singleton == null) {
			synchronized(Singleton.class) {
				for(int i=0;i<100000;i++) {
					
				}
				
				if(singleton == null) {
					singleton = new Singleton();
				}
				
			}
			
		}
		return singleton;
	}
	
	public static void main(String[] args) {
		new Thread() {
			public void run() {
				Singleton.getInstance();
			};
		}.start();
		
		new Thread() {
			public void run() {
				Singleton.getInstance();
			};
		}.start();
		
	}
}
