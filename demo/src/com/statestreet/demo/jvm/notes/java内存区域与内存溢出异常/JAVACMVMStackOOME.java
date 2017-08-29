package com.statestreet.demo.jvm.notes.java内存区域与内存溢出异常;

/**
 * 虚拟机栈和本地方法区溢出
 * VM: -Xss5M(虚拟机栈设置为大点的话则内存溢出更快，因为该参数是针对每个线程的)
 * @author Administrator
 *
 */
public class JAVACMVMStackOOME {

	private void dontStop(){
		while(true){
			
		}
	}
	
	public void stackLeakByThread(){
		while(true){
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					dontStop();
				}
			});
			
			t.start();
			
		}
		
	}
	
	
	public static void main(String[] args) {
		JAVACMVMStackOOME oom = new JAVACMVMStackOOME();
		oom.stackLeakByThread();
		
	}
}
