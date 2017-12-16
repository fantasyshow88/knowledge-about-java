package com.statestreet.demo.java.note;
/**
 * loadClass() 父类的委托机制都在这里实现，一般不覆盖
 * findClass() 父类做不了的交给子类去做 如果自己写ClassLoader一般重写此方法
 * defineClass()  将类转化为字节码
 * @author Administrator
 *
 */
public class MyClassLoader extends ClassLoader{

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		return super.findClass(name);
	}
	
}
