package com.statestreet.demo.java.thread.basic;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newCachedThreadPool();
		
		CountDownLatch command = new CountDownLatch(1);
		CountDownLatch participant = new CountDownLatch(3);
		for(int i=0;i<3;i++) {
			Runnable r = new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + " 等待主线程命令");
						command.await();
						System.out.println(Thread.currentThread().getName() + "收到主线程命令，开始执行子线程~");
						Thread.sleep(new Random().nextInt(3000));
						participant.countDown();
						System.out.println(Thread.currentThread().getName() + "子线程结束");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			pool.execute(r);
		}
		
		Thread.sleep(2000);
		System.out.println(Thread.currentThread().getName() + "发送主线程命令给子线程");
		command.countDown();
		
		participant.await();
		System.out.println(Thread.currentThread().getName() + "全部执行完毕！");
		pool.shutdown();
	}
}
