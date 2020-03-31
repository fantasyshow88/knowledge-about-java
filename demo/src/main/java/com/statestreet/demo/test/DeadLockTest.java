package com.statestreet.demo.test;

public class DeadLockTest {
	public static Object o1 = new Object();
	public static Object o2 = new Object();
	public static Object o3 = new Object();
	
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (o1) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (o2) {
						
					}
				}
				
			}
		}).start();;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (o2) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (o3) {
						
					}
					
					
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (o3) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (o1) {
						
					}
					
					
				}
			}
		}).start();
		
	}
}
