package com.statestreet.demo.java.thread.basic;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableAndFuture {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		callableAndFutureTest();
		
		completionServiceTest();
		
	}

	private static void completionServiceTest() throws InterruptedException, ExecutionException {
		ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
		//用于提交一组任务 其take方法返回已经完成的callable 任务的future
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
		//提交10个体任务
		for(int i = 1;i<=10;i++) {
			final int task = i;
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return task;
				}
			});
		}
		//获取任务的结果
		for(int i = 1;i<=10;i++) {
			System.out.println("task : " + completionService.take().get());
		}
		threadPool2.shutdown();
	}

	private static void callableAndFutureTest() throws InterruptedException, ExecutionException {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<String> future = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "hello";
			}
		});
		
		System.out.println("等待结果...");
		String result = future.get();
		System.out.println("拿到结果  : " + result);
		threadPool.shutdown();
	}
}
