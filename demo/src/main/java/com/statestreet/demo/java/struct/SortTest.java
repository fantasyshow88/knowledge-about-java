package com.statestreet.demo.java.struct;

import java.util.Arrays;

public class SortTest {


	public static void main(String[] args) {
		int[] a = {10,1,4,9,8,3};

//		bubbleSort2(a);
//		selectSort3(a);
//		selectSort(a);
		selectSort4(a);
//		quickSort(a,0,a.length-1);
		System.out.println(Arrays.toString(a));


	}

	/** 1 2 4 3 6 5
	 *  1 2 3 4 6 5
	 *
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

	private static void insertSort2(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int temp = a[i];
			int j = 0;
			for(j = i -1;j>=0 && a[j] > temp;j--){
				a[j+1] = a[j];
			}
			a[j+1] = temp;
		}


	}


	/**
	 * test
	 * @param a
	 */
	private static void insertSort3(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int temp = a[i];
			int j = 0;
			for (j = i-1; j >=0 && a[j] > temp; j--) {
				a[j + 1] = a[j];
			}
			a[j +1] = temp;
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
	 *
	 * @param a
	 * @param i
	 * @return
	 */
	private static int binarySearch3(int[] a, int i) {
		int low = 0,high = a.length;
		while (low <= high){
			int mid = low + (high - low)/2;
			if(a[mid] == i){
				return mid;
			}else if( a[mid] > i){
				high = mid -1;
			}else if(a[mid] < i){
				low = mid + 1;
			}
		}
		return -1;
	}

	private static int binarySearch2(int[] a, int i) {
		int low = 0,high = a.length-1;
		while (high >= low){
			int mid = low + (high-low)/2;
			if(a[mid] == i){
				return mid;
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
				if(a[j] < a[k]) {
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
	 * {2,1,4,9,8,3};
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
	 * test 每次选最小的放最前面
	 * @param a
	 */
	private static void selectSort3(int[] a) {
		for (int i = 0; i < a.length-1; i++) {
			int min = i;

			for (int j = i+1; j < a.length; j++) {
				if(a[j] < a[min]){
					min = j;
				}
			}
			if(min != i){
				int t = a[min];
				a[min] = a[i];
				a[i] = t;
			}

		}
	}

	private static void selectSort4(int[] a) {
		for (int i = 0; i < a.length-1; i++) {
			int min = i;
			for (int j = i+1; j < a.length; j++) {
				if(a[j] < a[min]){
					min = j;
				}
			}
			if(min != i){
				int temp = a[i];
				a[i] = a[min];
				a[min] = temp;
			}
		}
	}


	/**
	 * 递归方法实现的快速排序
	 * @param a
	 * @param startIndex
	 * @param endIndex
	 */
	private static void quickSort(int[] a, int startIndex, int endIndex){
		if(startIndex >= endIndex){
			return;
		}
		int pivotIndex = partition(a,startIndex,endIndex);
		quickSort(a,startIndex,pivotIndex-1);
		quickSort(a,pivotIndex + 1,endIndex);
	}

	private static int partition(int[] a, int startIndex, int endIndex) {
		int pivot = a[startIndex];
		int left = startIndex;
		int right = endIndex;
		int index = startIndex;
		while(left <= right){
			while (left <= right){
				if(a[right] < pivot){
					a[left] = a[right];
					index = right;
					left++;
					break;
				}
				right--;
			}
			while (left <= right){
				if(a[left] > pivot){
					a[right] = a[left];
					right--;
					index = left;
					break;
				}
				left++;
			}
		}
		a[index] = pivot;
		return index;
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


	private static void bubbleSort2(int[] a) {
		for (int i = 0; i < a.length-1; i++) {
			for (int j = 0; j < a.length-1-i; j++) {
				if(a[j+1] < a[j]){
					int t = a[j+1];
					a[j + 1] = a[j];
					a[j] = t;
				}
			}
		}
	}




}
