package com.statestreet.demo.java.thread.basic;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimulateArrayBlockQueue {

	Object[] items = new Object[100];
		
	Lock lock = new ReentrantLock();
	Condition isNotFull = lock.newCondition();
	Condition isNotEmpty = lock.newCondition();

	int putIndex,takeIndex,count;
	
	public void put(Object o) throws InterruptedException {
		lock.lock();
		try {
			while(count == items.length) {//已经满了 再不 await 取的话就要 outofbound error 了
				isNotFull.await();//isNotFull为true 的话才会put，现在已经满了就await
			}
			items[putIndex++] = o;
			if(putIndex == items.length) {
				putIndex = 0;
			}
			count ++;
			isNotEmpty.signal();
		}finally {
			lock.unlock();
		}
		
	}
	
	
	public Object take() throws InterruptedException {
		lock.lock();
		Object o = null;
		try {
			while(count == 0) {//为空则await
				isNotEmpty.await();
			}
			o = items[takeIndex++];
			if(takeIndex == items.length) {
				takeIndex = 0;
			}
			count--;
			isNotFull.signal();
			return o;
		}finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		SimulateArrayBlockQueue queue = new SimulateArrayBlockQueue();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(new Random().nextInt(1000));
						int data = new Random().nextInt(10000);
						System.out.println(Thread.currentThread().getName() + " be ready to put data: " + data);
						queue.put(data);
						System.out.println(Thread.currentThread().getName() + " have put data: " + data);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(new Random().nextInt(1500));
						System.out.println(Thread.currentThread().getName() + " be ready to get data." );
						Object o = queue.take();
						System.out.println(Thread.currentThread().getName() + " have get data: " + o );
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
}









