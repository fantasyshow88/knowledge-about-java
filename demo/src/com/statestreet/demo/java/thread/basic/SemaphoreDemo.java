package com.statestreet.demo.java.thread.basic;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * semaphore 可以维护当前访问自身的线程个数，可以控制同时访问资源的线程个数，比如一个文件允许的并发个数
 * @author Administrator
 *美 [ˈsɛməˌfɔr, -ˌfor]
 */
public class SemaphoreDemo {

	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newCachedThreadPool();
		Semaphore s = new Semaphore(3);//如果是一个灯的话可以当做synchronized 来实现线程互斥效果一样
		for(int i=0;i<10;i++) {
			Runnable r = new Runnable() {
				
				@Override
				public void run() {
					try {
						s.acquire();//获取灯
						
						System.out.println("线程 " + Thread.currentThread().getName() + " 进入,当前并发数: " + (3-s.availablePermits()));
						
						Thread.sleep(new Random().nextInt(10000));
						System.out.println("线程 " + Thread.currentThread().getName() + " 即将离开");
						s.release();//释放灯
						System.out.println("线程 " + Thread.currentThread().getName() + " 已离开,当前并发数: " + (3-s.availablePermits()));
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			
			
			pool.execute(r);
		}
		
		pool.shutdown();
		
	}
	
}
