package com.statestreet.demo.java.thread.basic;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

	public static void main(String[] args) {
		Queue q = new Queue();
		for(int i = 0;i<3;i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						q.get();
					}
				}
			}).start();

			
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						q.put(new Random().nextInt(10000));;
					}
				}
			}).start();
			
		}
		
		
	}
	
}

class Queue{
	
	
	ReadWriteLock wrLock = new ReentrantReadWriteLock();
	
	private Object data = null;
	
	/**
	 * 在读锁中别的线程只能读操作，不能在读的时候写，此时上读锁可以实现
	 */
	public void get() {
		wrLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to read data !");
			Thread.sleep(new Random().nextInt(2000));
			System.out.println(Thread.currentThread().getName() + " have read data : " + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			wrLock.readLock().unlock();
		}
		
		
	}
	
	/**
	 * 在写的时候别的线程不能读也不能写，可以上写锁
	 * @param data
	 */
	public void put(Object data) {
		wrLock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to write data !");
			Thread.sleep(new Random().nextInt(2000));
			this.data = data;
			System.out.println(Thread.currentThread().getName() + " hava write data: " + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			wrLock.writeLock().unlock();
		}
	}
	
}