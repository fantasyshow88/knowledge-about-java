package com.statestreet.demo.java.thread.basic;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TraditionalThread {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
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
		
		//future task implements interface runnable
		FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				Thread.sleep(new Random().nextInt(3000));
				return new Random().nextInt(100);
			}
		});
		
		Thread t4 = new Thread(futureTask);
		t4.start();
		System.out.println(futureTask.get());
	}
}
