package com.fanlan.fighterdemo.thread;

public class demo {

    public static void main(String[] args) {

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                System.out.println("线程"+Thread.currentThread().getName()+i);
            }
        }).start();
    }
}
