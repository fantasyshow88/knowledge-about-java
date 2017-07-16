package lambda.self;

public class Test2 {

	public static void main(String[] args) {
		System.out.println(new RunnableTest2() {
			
			@Override
			public String run() {
				return "hahaha";
			}
		}.run());;
		//////////////////////////
		
		RunnableTest2 t2 = ()->{return "aaa";};
		System.out.println(t2.run());
		
		RunnableTest2 t3 = ()-> "bbb";
		System.out.println(t3.run());
	}
	
}
