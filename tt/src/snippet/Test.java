package snippet;

import java.util.PriorityQueue;

import com.statestreet.demo.test.Person;


public class Test {

	public static void main(String[] args) {
		PriorityQueue<Person> queue = new PriorityQueue<Person>();
		Person p = new Person("aa", 11);
		
		Person p2 = new Person("cc", 11);
		
		Person p3 = new Person("bb", 11);
		
		queue.add(p);
		queue.add(p2);
		queue.add(p3);
		
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}
}

