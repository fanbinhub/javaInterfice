package com.util.demo;

public class Caluculator {
    public static int result=0;

    public static int add(int x,int y) throws InterruptedException {
        result = x + y;
        Thread.sleep(1000);
        return result;
    }
}
