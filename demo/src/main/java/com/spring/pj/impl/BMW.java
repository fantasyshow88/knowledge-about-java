package com.spring.pj.impl;

import com.spring.pj.Car;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-03-11 14:02
 */
public class BMW implements Car {

    @Override
    public void run() {
        System.out.println("IM AM BMW~");
    }
}
