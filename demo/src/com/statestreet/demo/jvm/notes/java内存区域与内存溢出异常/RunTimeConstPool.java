package com.statestreet.demo.jvm.notes.java内存区域与内存溢出异常;

import java.util.ArrayList;
import java.util.List;

/**方法区和运行时常量池溢出
 * VM:-XX:PermSize=10M -XX:MaxPermSize=10M
 * @author Administrator
 *
 */
public class RunTimeConstPool {

	public static void main(String[] args) {
		List list = new ArrayList();
		int i = 0;
		while(true){
			list.add(String.valueOf(i+1).intern());
		}
	}
}
