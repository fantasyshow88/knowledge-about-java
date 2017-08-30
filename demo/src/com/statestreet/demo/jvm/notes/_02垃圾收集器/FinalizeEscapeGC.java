package com.statestreet.demo.jvm.notes._02垃圾收集器;

public class FinalizeEscapeGC {

	public static FinalizeEscapeGC SAVE_HOOK = null;
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		
		
		System.out.println("finalize method executed!");
		
		//唯一的一次逃脱被回收的机会，因为finalize 方法只能被执行一次
		SAVE_HOOK = this;
	}
	
	public static void main(String[] args) throws Exception {
		SAVE_HOOK = new FinalizeEscapeGC();
//		System.out.println(SAVE_HOOK);
		SAVE_HOOK = null;
		
		//调用该方法 看有哪些对象可以回收，如果有的话就调用待回收对象的 finalize() 方法
		System.gc();
		//finalize 方法优先级很低  等待500 毫秒
		Thread.sleep(500);
		
		if(SAVE_HOOK == null){
			System.out.println("i am dead!");
		}else {
			System.out.println("i am still alive!");
		}
		
		///////////////////////////////////////////////////////////////
	//	System.out.println(SAVE_HOOK);
		SAVE_HOOK = null;
		
		//第二次调用  即使有待回收的对象(SAVE_HOOK) 也不执行 finalize 方法了 ，因为该对象已经执行过一次 finalize 方法，不会执行第二次了，所以第二次逃脱失败
		System.gc();
		
		Thread.sleep(500);
		
		if(SAVE_HOOK == null){
			System.out.println("i am dead!");
		}else {
			System.out.println("i am still alive!");
		}
		
	}
	
}
