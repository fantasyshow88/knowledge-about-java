package com.statestreet.demo.java.thread.basic;

import java.util.concurrent.CompletableFuture;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-08-19 15:46
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        //supplyAsync内部使用ForkJoinPool线程池执行任务
        CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("task doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "100";
        }).whenComplete((v,r)->{
            System.out.println("计算结果是: "+v);
        });
        CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("task2 doing...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "22";
        }).whenComplete((v,r)->{
            System.out.println("2计算结果是: "+v);
        });
        for(;;){}
    }


}
