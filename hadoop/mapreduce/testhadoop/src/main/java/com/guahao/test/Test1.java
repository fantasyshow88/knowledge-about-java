package com.guahao.test;

import org.junit.Test;

/**
 * @author Xu Jianglin
 * @create 2019-01-23 11:25
 */
public class Test1 {




    @Test
    public void test1(){
        String dir = "C:\\Users\\weiyi";
        String path= "~/data/";
        path = path.replaceFirst("~",dir);
        System.out.println(path);

        System.out.println("~/data/".replaceFirst("~","C:\\\\atest"));
        System.out.println("a" + "\\" + "b");
    }

}
