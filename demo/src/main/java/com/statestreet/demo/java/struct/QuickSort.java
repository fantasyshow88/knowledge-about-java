package com.statestreet.demo.java.struct;

import java.util.Arrays;

/**快速排序
 *https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653195042&idx=1&sn=2b0915cd2298be9f2163cc90a3d464da&chksm=8c99f9f8bbee70eef627d0f5e5b80a604221abb3a1b5617b397fa178582dcb063c9fb6f904b3&scene=21#wechat_redirect
 *快速排序的平均时间复杂度是 O（nlogn），最坏情况下的时间复杂度是 O（n^2）
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-04-26 11:29
 */
public class QuickSort {


    /**
     * 递归方式实现的快速排序
     * @param arr
     * @param startIndex
     * @param endIndex
     */
    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        // 递归结束条件：startIndex大等于endIndex的时候
        if (startIndex >= endIndex) {
            return;
        }
        // 得到基准元素位置
        int pivotIndex = partition(arr, startIndex, endIndex);
        // 用分治法递归数列的两部分
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        // 坑的位置，初始等于pivot的位置
        int index = startIndex;
        //大循环在左右指针重合或者交错时结束
        while ( right >= left  ){
            //right指针从右向左进行比较
            while ( right >= left ) {
                if (arr[right] < pivot) {
                    arr[left] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            //left指针从左向右进行比较
            while ( right >= left ) {
                if (arr[left] > pivot) {
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = pivot;
        return index;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {4,7,6,5,3,2,9,8};
        quickSort2(arr, 0, arr.length-1);
//        quickSort2(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort2(int[] a, int startIndex,int endIndex){
        if( startIndex >= endIndex){
            return;
        }
        int pivotIndex = partition2(a, startIndex, endIndex);
        quickSort2(a, startIndex, pivotIndex-1);
        quickSort2(a,pivotIndex +1,endIndex);
    }

    private static int partition2(int[] a, int startIndex, int endIndex) {
        int leftIndex = startIndex;
        int rightIndex = endIndex;
        int pivot = a[startIndex];
        int holeIndex = startIndex;
        while (leftIndex <= rightIndex){
            while (leftIndex <= rightIndex){
                if(a[rightIndex] < pivot){
                    a[leftIndex] = a[rightIndex];
                    leftIndex ++;
                    holeIndex = rightIndex;
                    break;
                }
                rightIndex--;
            }

            while (leftIndex <= rightIndex){
                if(a[leftIndex] > pivot){
                    a[rightIndex] = a[leftIndex];
                    rightIndex--;
                    holeIndex = leftIndex;
                    break;
                }
                leftIndex ++;
            }
        }
        a[holeIndex] = pivot;
        return holeIndex;
    }


    public static void quickSort3(int[] arr, int startIndex,int endIndex){
        if(startIndex >= endIndex){
            return;
        }
        int pivotIndex = partition3(arr, startIndex, endIndex);
        quickSort3(arr, 0, pivotIndex-1);
        quickSort3(arr, pivotIndex+1, endIndex);

    }

    private static int partition3(int[] arr, int startIndex, int endIndex) {
        int index = startIndex;
        int left = startIndex;
        int right = endIndex;
        int pivot = arr[startIndex];
        while (right >= left){
            while (right >= left){//right
                if(arr[right] < pivot){
                    arr[left] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                right --;
            }

            while (right >= left){//left
                if(arr[left] > pivot){
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = pivot;
        return index;
    }

    public static void quickSort4(int[] arr, int startIndex, int endIndex) {
        if(startIndex >= endIndex){
            return;
        }
        int pivotIndex = partition4(arr,startIndex,endIndex);
        quickSort4(arr, 0, pivotIndex-1);
        quickSort4(arr,pivotIndex +1,endIndex);
    }

    private static int partition4(int[] arr, int startIndex, int endIndex) {
        int left = startIndex;
        int right = endIndex;
        int pivot = arr[startIndex];
        int holeIndex = startIndex;
        while (right >= left){

            while (right >=left){
                //right
                if(arr[right] < pivot){
                    arr[left] = arr[right];
                    holeIndex = right;
                    left++;
                    break;
                }
                right--;
            }

            while (right >= left){
                if(arr[left] > pivot){
                    arr[right] = arr[left];
                    holeIndex = left;
                    right --;
                    break;
                }
                left++;
            }
        }
        arr[holeIndex] = pivot;
        return holeIndex;
    }
}
