package com.statestreet.demo.java.thread.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {

	
	ReadWriteLock wrLock = new ReentrantReadWriteLock();
	
	private Map<String,Object> cache = new HashMap<String,Object>();
	
	public Object getData(String key) {
		wrLock.readLock().lock();
		Object data = cache.get(key);
		try {
			if(data == null) {
				wrLock.readLock().unlock();
				wrLock.writeLock().lock();
				try {
					if(data == null) {
						data = "11";//query in DB
					}
					cache.put(key, data);
				}finally {
					wrLock.writeLock().unlock();
				}
				wrLock.readLock().lock();//恢复读锁
				
			}
		}finally {
			wrLock.readLock().unlock();
		}
		return data;
	}
	
	public static void main(String[] args) {
		System.out.println(new CacheDemo().getData("a"));
	}
	
}
