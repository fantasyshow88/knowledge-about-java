package com.statestreet.demo.java.thread.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * @author Administrator
 *
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
//		ExecutorService executorService = Executors.newFixedThreadPool(3);
//		ExecutorService executorService = Executors.newCachedThreadPool();//缓存线程池 线程数动态变化
		ExecutorService executorService = Executors.newSingleThreadExecutor();//单线程，保证线程池里有一个active的线程, 线程死了又会有一个启动
		for(int i = 0;i<10;i++) {
			final int task = i;
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " for task of " + task);
				}
			});
			
		}
		System.out.println("all of 10 task have committed");
		executorService.shutdown();
//		executorService.shutdownNow(); 马上关闭 不管task 有没有执行完毕
		
		Executors.newScheduledThreadPool(3).schedule(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("bombing");
			}
		}, 2, TimeUnit.SECONDS);
		
		
		
	}
}
