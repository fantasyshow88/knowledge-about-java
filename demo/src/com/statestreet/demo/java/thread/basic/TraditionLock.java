package com.statestreet.demo.java.thread.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TraditionLock {

	public static void main(String[] args) {
		new TraditionLock().init();
		
	}
	
	public void init() {
		Outputer out = new Outputer();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					out.output("zhangxiaoxiang");
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					out.output("xujianglin");
				}
			}
		}).start();
	}
	static class Outputer{
		
		Lock lock = new ReentrantLock();
		//use this as monitor 
		public void  output(String name) {
			lock.lock();
			try {
				for(int i = 0;i<name.length();i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}finally {
				//放finally 确保解锁
				lock.unlock();
			}
			
		}
		
		
	}
}
