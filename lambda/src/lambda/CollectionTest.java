package lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionTest {

	public static void main(String[] args) {
		
		List<String> list = Arrays.asList("cv","asc","fs");
		Collections.sort(list, new Comparator<String>() {
			
			public int compare(String o1, String o2) {
				
				return o1.compareTo(o2);
			};
		});
		
		System.out.println(list);
	
		//////////////////////////////////////////////////
		List<Integer> ll = Arrays.asList(4,1,5);
		Collections.sort(ll,(o1,o2)->{
			return o1.compareTo(o2);
			
		});
		System.out.println(ll);
		
		List<Integer> l2 = Arrays.asList(14,12,5,1);
		Collections.sort(l2,(o1,o2)->o1.compareTo(o2)
		);
		System.out.println(l2);
	}
}
