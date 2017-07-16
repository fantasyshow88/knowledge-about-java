package foreach;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ForeachTest {

	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(14);
		list.add(5);
		list.add(8);
		
		list.forEach(new Consumer<Integer>() {
			
			@Override
			public void accept(Integer t) {
				System.out.println("for: " + t);
			}
		});
		
		
		list.forEach((t)->System.out.println(t));
		
		
		
	}
}
