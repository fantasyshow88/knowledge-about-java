package com.statestreet.demo.java.thread.basic;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerDemo {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		Exchanger ex = new Exchanger<>();
		pool.execute(new Runnable() {
			@Override
			public void run() {
				String dollar = "1000$";
				System.out.println(Thread.currentThread().getName() + "将要把 " + dollar + "换出去...");
				try {
					Thread.sleep(new Random().nextInt(2000));
					String baifeng = (String)ex.exchange(dollar);
					System.out.println(Thread.currentThread().getName() + "换回来的数据是 " + baifeng);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		pool.execute(new Runnable() {
			@Override
			public void run() {
				String baifeng = "baifeng";
				System.out.println(Thread.currentThread().getName() + "将要把 " + baifeng + "换出去...");
				try {
					Thread.sleep(new Random().nextInt(2000));
					String dollar = (String)ex.exchange(baifeng);
					System.out.println(Thread.currentThread().getName() + "换回来的数据是 " + dollar);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		pool.shutdown();
	}
}
