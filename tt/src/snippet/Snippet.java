package snippet;

import java.util.Arrays;
import java.util.Vector;

public class Snippet {
	
	public static void main(String[] args) {
		Vector v = new Vector();
		for (int i = 1; i<100; i++)
		{
			Object o = new Object();
			v.add(o);
			o = null;
		}
		System.out.println(v);
		
		String s = "a,a";
		String[] a = s.split(",");
		System.out.println(Arrays.toString(a));
		String aa = new String("aaaa");
		
		ThreadLocal<String> local = new ThreadLocal<String>();
		local.set("aa1");
		System.out.println(local.get());
	}
}

