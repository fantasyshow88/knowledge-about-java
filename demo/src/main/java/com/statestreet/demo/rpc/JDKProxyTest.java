package com.statestreet.demo.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyTest {

	public static void main(String[] args) {
		HelloService h = new HelloService() {
			@Override
			public String sayHi(String name) {
				return "sayHi-->" + name;
			}

			@Override
			public String getName(String name) {
				return name + ":)";
			}
		};
		
		
		HelloService helloImpl = (HelloService)Proxy.newProxyInstance(HelloService.class.getClassLoader(), new Class[] {HelloService.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("before "+ method.getName() +" executed...");
				Object result = method.invoke(h, args);
				System.out.println("after "+ method.getName() +" executed...");
				return result;
			}
		});
		helloImpl.sayHi("bike");
		System.out.println("--------------------------");
		String result = helloImpl.getName("mike");;
		System.out.println(result);
	}
}

