package snippet;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.Vector;

public class Snippet {
	
	public static void main(String[] args) {
		TreeMap<Person, String> treeMap = new TreeMap<Person, String>();
		Person p = new Person("aaa");
		Person p2 = new Person("1tes");
		treeMap.put(p, "aa");
		treeMap.put(p2, "bb");
		System.out.println(treeMap);
	}
	
	static class Person{
		private String name;

		public Person(String name) {
			super();
			this.name = name;
		}
		
		
	}
}


