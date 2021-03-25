package com.statestreet.demo.java.struct;

import java.util.HashMap;

/**
 * 用动态规划解决(由最优子结构(f(10) = f(9) + f(8),f(9)和f(8)是f(10)的最优子结构),边界(f(2) = 2, f(1) = 1),状态转移方程(fn) = f(n-1) +f(n-2) )
 * 爬楼梯, 每次只能爬一阶或两阶,问爬到n阶有几种爬法
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-03-25 15:53
 */
public class Dynamic {

    /**
     *递归方案,时间复杂度较高(2^n)
     * @param n
     * @return
     */
    public static int crimb(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        return crimb(n - 1) + crimb(n - 2);
    }

    /**
     * 备忘录算法->减小时间复杂度, 自顶向下
     * 因为crimb()中很多f()重复计算了, 比如 f(n-2)就计算了两次,可以先把计算结果缓存起来,减少相同的f()函数计算次数,提高效率
     * 时间复杂度O(n),可是空间复杂度还有待提高
     * @return
     */
    public static int memoCrimb(int n, HashMap<Integer,Integer> map){
        if(n < 1){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        if(map.containsKey(n)){
            return map.get(n);
        }else{
            int val = memoCrimb(n - 1,map) + memoCrimb(n - 2,map);
            map.put(n, val);
            return val;
        }
    }

    /**
     * f(n) = f(n-1) + f(n-2), f(1)=1,f(2)=2
     * @param n 爬楼梯的总数目
     * @return
     */
    public static int dynamic2(int n){
        if(n < 0){
            return 0;
        }
        if(n == 1 || n == 2){
          return n;
        }
        int c=0;
        int a=0,b=0;
        for (int i = 3; i <= n; i++) {
            if(i == 3){
                a = 1;
                b =2;
            }else {
                a = b;
                b = c;
            }
            c = a + b;
        }

        return c;
    }


    /**
     * 真正的动态规划算法, 自底向上, 时间复杂度O(n),可是空间复杂度较低,相对于备忘录算法
     * @param n
     * @return
     */
    public static int dynamic(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        int a = 1;
        int b = 2;
        int temp = 0;
        for(int i = 3;i<=n;i++){
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }


    public static void main(String[] args) {
/*        HashMap<Integer, Integer> map = new HashMap<>();
        long start = System.currentTimeMillis();
        System.out.println(memoCrimb(45,map));
        long middle = System.currentTimeMillis();
        System.out.println(middle-start);
        System.out.println(crimb(45));
        long end = System.currentTimeMillis();
        System.out.println(end -middle);
        System.out.println(dynamic(45));
        System.out.println(System.currentTimeMillis() - end);*/
        System.out.println(dynamic2(10));
        System.out.println(dynamic(10));
    }

}
