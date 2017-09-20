package com.statestreet.demo.java.thread.basic;

/**
 * 获取线程相关的对象，只要调用getInstance 就自动获取和当前线程相关的对象
 * 不需要用synchronize 修饰 getInstance 方法了
 * @author Administrator
 *
 */
public class ThreadLocalSingleton {

	public static ThreadLocalSingleton getInstance() {
		ThreadLocalSingleton instance = map.get();
		if(instance == null) {
			instance = new ThreadLocalSingleton();
		}
		map.set(instance);
		return instance;
	}
	
	private static ThreadLocal<ThreadLocalSingleton> map = new ThreadLocal<ThreadLocalSingleton>();

	
	public static void main(String[] args) {
		
		for(int i = 0;i<3;i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("线程 " + Thread.currentThread().getName() + " put data " + ThreadLocalSingleton.getInstance());
					System.out.println("A 从线程 " + Thread.currentThread().getName() + " get data " + ThreadLocalSingleton.getInstance());
					System.out.println("B 从线程 " + Thread.currentThread().getName() + " get data " + ThreadLocalSingleton.getInstance());
				}
			}).start();
		}
		
	}
}
