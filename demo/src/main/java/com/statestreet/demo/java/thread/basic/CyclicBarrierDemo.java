package com.statestreet.demo.java.thread.basic;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 等待大家都到了之后再做某件事
 * @author Administrator
 *
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		CyclicBarrier cb = new CyclicBarrier(3);
		//3或者3的倍数，不然会有人一直在那边等，结束不了
		for(int i=0;i<3;i++) {
			Runnable r = new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(new Random().nextInt(3000));
						System.out.println(Thread.currentThread().getName() + " 即将到达集合点1,当前有 "+ (cb.getNumberWaiting() + 1) + " 已经到达," + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走~" : ""));
						cb.await();
						
						Thread.sleep(new Random().nextInt(3000));
						System.out.println(Thread.currentThread().getName() + " 即将到达集合点2,当前有 "+ (cb.getNumberWaiting() + 1) + " 已经到达," + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走~" : ""));
						cb.await();
						
						Thread.sleep(new Random().nextInt(3000));
						System.out.println(Thread.currentThread().getName() + " 即将到达集合点3,当前有 "+ (cb.getNumberWaiting() + 1) + " 已经到达," + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走~" : ""));
						cb.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			pool.execute(r);
		}
		pool.shutdown();
		
	}
	
}
