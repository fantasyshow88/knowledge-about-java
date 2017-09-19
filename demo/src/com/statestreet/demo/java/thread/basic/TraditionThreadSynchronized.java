package com.statestreet.demo.java.thread.basic;

public class TraditionThreadSynchronized {

	public static void main(String[] args) {
		new TraditionThreadSynchronized().init();
		
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
		//use this as monitor 
		public synchronized void  output(String name) {
			for(int i = 0;i<name.length();i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		//use Outputer.class as monitor
		public static synchronized void  output2(String name) {
			for(int i = 0;i<name.length();i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		
	}
}
