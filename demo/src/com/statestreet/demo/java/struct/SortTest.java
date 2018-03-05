package com.statestreet.demo.java.struct;

import java.util.Arrays;

public class SortTest {

	
	public static void main(String[] args) {
		int[] a = {2,1,4,9,8,3};
		
//		bubbleSort(a);
//		selectSort(a);
//		selectSort2(a);
		insertSort(a);
		System.out.println(Arrays.toString(a));
		
		
	}
	
	/**
	 * 插入排序
	 * @param a
	 */
	private static void insertSort(int[] a) {
	    // 第1个数肯定是有序的，从第2个数开始遍历，依次插入有序序列
	    for (int i = 1; i < a.length; i++) {
	        int j = 0;
	        int temp = a[i]; // 取出第i个数，和前i-1个数比较后，插入合适位置
	 
	        // 因为前i-1个数都是从小到大的有序序列，所以只要当前比较的数(list[j])比temp大，就把这个数后移一位
	        for (j = i - 1; j >= 0 && temp < a[j]; j--) {
	            a[j + 1] = a[j];
	        }
	        a[j + 1] = temp;
	    }
	}
	
	
	private static int binarySearch(byte[] a, byte i) {
		int low = 0,high = a.length-1;
		
		while(low <= high){
			int mid = low + (high - low)/2;
			int mid2 = (low + high) >>>1;
			//int mid = (high + low)/2;
		//	System.out.println(high +"|| " +  low);
			//int all = high + low;
			//System.out.println(all);
			//System.out.println(all >>>1);
		//	System.out.println(all /2);
//	        System.out.println(mid + " *** "+ mid2);
			if(a[mid] == i){
				return a[mid];
			}else if(a[mid] > i){
				high = mid -1;
			}else if(a[mid] < i){
				low = mid + 1;
				
			}
		}
		return -1;
	}

	/**
	 * 二分查找
	 * @param a
	 * @param i
	 * @return
	 */
	private static int binarySearch(int[] a, int i) {
		int low = 0,high = a.length-1;
		
		while(low <= high){
			int mid = low + (high - low)/2;
			int mid2 = (low + high) >>>1;
	        System.out.println(mid + " *** "+ mid2);
			if(a[mid] == i){
				return a[mid];
			}else if(a[mid] > i){
				high = mid -1;
			}else if(a[mid] < i){
				low = mid + 1;
				
			}
		}
		return -1;
	}
	
	/**
	 * more performance compared with selectSort
	 * @param a
	 */
	private static void selectSort2(int[] a) {
		for(int i = 0;i<a.length-1;i++) {
			int k= i;
			for(int j = i+1;j<a.length;j++) {
				if(a[i]>a[j]) {
					k = j;
				}
			}
			if(k != i) {
				int t = a[i];
				a[i] = a[k];
				a[k] = t;
			}
		}
	}
	
	/**
	 * 两个循环的值对比，每轮比出一个最大值
	 * @param a
	 */
	private static void selectSort(int[] a) {
		for(int i = 0;i<a.length-1;i++) {
			for(int j = i+1;j<a.length;j++) {
				if(a[i]>a[j]) {
					int t = a[i];
					a[i] = a[j];
					a[j] = t;
				}
			}
		}
	}




	/**
	 * 和相邻的数据对比（同一个循环里相互对比），一个一个对比，大的交换位置，每比一轮得到一个最大值或者最小值
	 * @param a
	 */
	private static void bubbleSort(int[] a) {
		for(int i = 0;i<a.length-1;i++) {
			for(int j = 0;j<a.length-i-1;j++) {
				if(a[j] > a[j+1]) {
					int t = a[j];
					a[j] = a[j+1];
					a[j+1] = t;
				}
			}
		}
		
	}
	
	
}
