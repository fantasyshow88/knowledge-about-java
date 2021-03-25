package com.statestreet.demo.java.struct;

import java.util.PriorityQueue;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2021-03-16 10:49
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        int[] a = new int[]{2,1,8,4,44,3};
        System.out.println(topKValue2(a,2));
    }


    /**
     * 返回数组种第k大小
     * @return
     */
    public static int topKValue(int[] a,int k){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : a) {
            if(queue.size() < k){
                queue.offer(i);
            }else if(queue.peek() < i){
                queue.poll();
                queue.offer(i);
            }
        }
        return queue.poll();
    }

    public static int topKValue2(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int n: nums) {
            heap.add(n);
            if (heap.size() > k){
                heap.poll();
            }
        }

        return heap.poll();
    }




}
