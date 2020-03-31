package com.statestreet.demo.java.thread.basic.interview;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * 10 个线程读取10个任务，按顺序一秒执行一个任务，即他们不是同时执行的，是串行执行的
 * @author Administrator
 *
 */
public class Test2 {

	public static void main(String[] args) throws Exception {
		System.out.println("begin:" + System.currentTimeMillis()/1000);
		Semaphore lamp = new Semaphore(1);
		//这两种都可以
//		ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(1);
		SynchronousQueue<String> q = new SynchronousQueue<>();
		for(int i = 0;i<10;i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						lamp.acquire();
						String s = q.take();
						parse(s);
						lamp.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		/**
		 * 也可以用锁来锁定保证线程按顺序串行执行
		 */
/*		for(int i = 0;i<10;i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						synchronized (Test2.class) {
							String s = q.take();
							parse(s);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}*/
		
		
		for(int i=0;i<10;i++) {
			String s = "" + (i+1);
			q.put(s);
		}
	}
	
	private static void parse(String log) {
		try {
			Thread.sleep(1000);
			System.out.println(log+ " : " + System.currentTimeMillis()/1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
