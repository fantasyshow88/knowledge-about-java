package com.statestreet.demo.jvm.notes.java内存区域与内存溢出异常;

/**
 * 虚拟机栈和本地方法区溢出
 * VM Args: -Xss128k
 * @author Administrator
 *
 */
public class JAVAVMStackSOFE {

	public void stackLeak(){
		stackLeak();
	}
	
	public static void main(String[] args) {
		JAVAVMStackSOFE oo = new JAVAVMStackSOFE();
		oo.stackLeak();
	}
	
}
