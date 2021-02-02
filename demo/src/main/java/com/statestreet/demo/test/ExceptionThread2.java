package com.statestreet.demo.test;

public class ExceptionThread2 implements Runnable{

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println("eh = " + t.getUncaughtExceptionHandler());

       // 手动抛出异常
        throw new RuntimeException();
    }
}
