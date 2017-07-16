package lambda;

public class RunnableTest {

	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("old runnable Test");
			}
		}).start();
		////////////////////////////////
		
		new Thread(()->System.out.println("new runnable test")).start();
		
	}
	
}
