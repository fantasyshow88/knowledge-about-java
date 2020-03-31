package com.statestreet.demo.java.thread.basic.copyonwrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionModifyExceptionTest {

	public static void main(String[] args) {
//		List list = new ArrayList<>();
		List list = new CopyOnWriteArrayList<>();
		list.add(new User("张三",11));
		list.add(new User("李四",22));
		list.add(new User("王五",33));
		Iterator it = list.iterator();
		while(it.hasNext()) {
			User u = (User)it.next();
			if("张三".equals(u.getName())) {
//				if("李四".equals(u.getName())) {
				list.remove(u);
//				it.remove();
			}else {
				System.out.println(u);
			}
			
		}
		
	}
}
