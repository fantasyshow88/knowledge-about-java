package com.statestreet.demo.java;
public enum TrafficLamp {
    RED(30){
        public  TrafficLamp nextLamp() {
            return GREEN ;
        }
    },
    GREEN(20){
        public  TrafficLamp nextLamp() {
            return YELLOW ;
        }
    },
    YELLOW(10){
        public  TrafficLamp nextLamp() {
            return RED ;
        }
    } ;
    public abstract TrafficLamp nextLamp() ;
    private int time ;
    private TrafficLamp(){
    }
    private TrafficLamp(int time){
        this.time = time ;
        System.out.println(time);
    }
    
    public static void main(String[] args) {
        System.out.println(TrafficLamp.GREEN);
    }
}