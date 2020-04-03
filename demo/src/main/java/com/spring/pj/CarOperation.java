package com.spring.pj;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-03-11 14:04
 */
public class CarOperation {

    private Car car;

    public void operate(){
        car.run();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
