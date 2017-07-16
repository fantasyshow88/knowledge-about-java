package lambda.self;

public class Test {

	public static void main(String[] args) {
		
		new RunnableTask() {
			
			@Override
			public void run() {
				System.out.println("old");
			}
		}.run();;
		
		
		/////
		
		
		RunnableTask task = ()->System.out.println("new");
		task.run();
		
		
	}
	
}
