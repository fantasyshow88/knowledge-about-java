package com.statestreet.demo.java.thread.basic;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-03-20 16:16
 */
public class Singleton2 {

    /**
     * 懒加载 用到的时候初始化
     */
    private static class LazySomethingHolder {
        public static Something something = new Something();
    }

    public static Something getInstance() {
        return LazySomethingHolder.something;
    }

    public static void main(String[] args) {
        System.out.println(Singleton2.getInstance());
    }

    private static class Something {
    }
}
