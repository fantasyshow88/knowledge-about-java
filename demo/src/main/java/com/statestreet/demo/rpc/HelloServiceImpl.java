package com.statestreet.demo.rpc;
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHi(String name) {
		return "HI -->" + name;
	}

	@Override
	public String getName(String name) {
		return name + ":)";
	}
 
 
}