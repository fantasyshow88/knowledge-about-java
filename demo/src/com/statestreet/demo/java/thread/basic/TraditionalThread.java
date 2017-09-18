package com.statestreet.demo.java.thread.basic;

public class TraditionalThread {

	public static void main(String[] args) {
		
		//define a thread with overwritten run method
		Thread t1 = new Thread() {
			public void run() {
				System.out.println(this.getName());
				currentThread().getName();
				Thread.currentThread().getName();
			};
		};
		t1.start();
		
		
		//define a thread with runnable instance
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		});
		
		t2.start();
		
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("runnable ~");
			}
		}) {
			public void run() {
				System.out.println("run method~");
			};
		};
		
		t3.start();//runnable~
		
	}
}
