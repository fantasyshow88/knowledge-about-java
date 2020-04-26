package com.statestreet.demo.java.struct;

/**
 * 是否是2的乘方
 *
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-03-26 14:13
 */
public class PowerOf2 {

    /**
     * 本身和本身-1 与运算为0 则是2的乘方
     *
     * @param n
     * @return
     */
    public static boolean isPowerOf2(int n) {
        return (n & (n - 1)) == 0;
    }

    /**
     * 返回转化为2进制后的1的个数
     *
     * @param n
     * @return
     */
    public static int countOf1ForBinaryFormat(int n) {
        int c = 0;
        for (c = 0; n > 0; ++c) {
            n &= (n - 1); // 清除最低位的1
        }
        return c;
    }


    public static void main(String[] args) {
        System.out.println(countOf1ForBinaryFormat(7));
    }
}
