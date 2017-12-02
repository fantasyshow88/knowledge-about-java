package com.statestreet.demo.java.java8.day01;


/**
 * 函数式接口
 * @FunctionalInterface 检查是不是函数式接口
 * 接口中只有一个抽象方法的接口为函数式接口
 * @author Administrator
 *
 * @param <T>
 */
@FunctionalInterface
public interface MyPredicate<T> {

	public boolean test(T t);
}
