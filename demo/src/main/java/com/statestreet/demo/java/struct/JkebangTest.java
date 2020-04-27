package com.statestreet.demo.java.struct;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-04-21 21:57
 */
public class JkebangTest {


    public static void main(String[] args) {
        System.out.println(power(2,5));
        System.out.println(power(2,-3));
    }

    /**
     * 计算 X^n次方的值， 分治算法时间复杂度 O(logN), 如果循环的相乘的话是O(N)
     * 递归算法
     * @return
     */
    public static double power(int x, int n){
        if(n == 1){
            return x;
        }
        if(n < 0){
            if(-n %2 == 0){
                return (1.0 / power(x, -n / 2)) * (1.0/power(x, -n / 2));
            }else{
                return (1.0 / power(x, -n / 2)) * (1.0/power(x, -n / 2)) * 1.0/x;
            }
        }else{
            if(n %2 == 0){//偶数
                return power(x, n / 2) * power(x, n / 2);
            }else{//奇数
                return x * power(x, n / 2) * power(x, n / 2);
            }

        }
    }





}
