package com.statestreet.demo.java.thread.juc.day01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * CountDownLatch ：闭锁，在完成某些运算是，只有其他所有线程的运算全部完成，当前运算才继续执行
 * 比如应用： 需分几个线程分别算出数码类库存，衣服类库存，食品类库存，最后当全部算好后再算出总库存
 */
public class TestCountDownLatch {

	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(10);
		LatchDemo ld = new LatchDemo(latch);

		long start = System.currentTimeMillis();

		ExecutorService ex = Executors.newFixedThreadPool(1);
		for (int i = 0; i < 10; i++) {
//			new Thread(ld).start();
			ex.execute(ld);
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
		}

		long end = System.currentTimeMillis();

		System.out.println("耗费时间为：" + (end - start));
	}

}

class LatchDemo implements Runnable {

	private CountDownLatch latch;

	public LatchDemo(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		synchronized (this) {
			try {
				for (int i = 0; i < 50000; i++) {
					if (i % 2 == 0) {
						System.out.println(i);
					}
				}
			} finally {
				latch.countDown();
			}
			
		}

	}

}