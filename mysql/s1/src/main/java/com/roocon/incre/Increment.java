package com.roocon.incre;

public class Increment implements Runnable {

	private Main m;
	
	public Increment(Main m) {
		this.m = m;
	}
	
	@Override
	public void run() {
		for(int i = 0;i<10;i++) {
			System.out.println(Thread.currentThread().getName() + " " + m.get());
		}
	}

}
