package com.fanlan.fightertest.singleton;

/**
 * 饿汉式
 */
public class Singleton1 {

    private static final Singleton1 sing=new Singleton1();

    private Singleton1(){

    }

    public static Singleton1 getSingleton1(){
        return sing;
    }
}
