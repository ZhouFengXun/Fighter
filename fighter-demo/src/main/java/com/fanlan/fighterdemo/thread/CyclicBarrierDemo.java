package com.fanlan.fighterdemo.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏屏障，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 * 直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行。
 * 简单理解就是：多个线程之间互相等待，满足条件在同一时间进行。
 */
public class CyclicBarrierDemo  extends Thread {
    private final CyclicBarrier barrier;
    //随机值处理
    private final Random random = new Random();
    public CyclicBarrierDemo(String name,CyclicBarrier barrier) {
        super(name);
        this.barrier = barrier;
    }

    /**
     * 重写run方法
     */
    @Override
    public void run() {
        try {
            Thread.sleep(random.nextInt(2000));
            System.out.println(Thread.currentThread().getName() + " - 已经到达旅行团");
            barrier.await();
            Thread.sleep(random.nextInt(2000));
            System.out.println(Thread.currentThread().getName() + " - 证件已经办理好");
            barrier.await();
            Thread.sleep(random.nextInt(2000));
            System.out.println(Thread.currentThread().getName() + " - 已经上旅行车");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        super.run();
    }

    public static void main(String[] args) {
        //旅行团5个游客
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5);
        for (int i=0;i<5;i++){
            new CyclicBarrierDemo("游客-" + (i + 1), cyclicBarrier).start();
        }
    }
}
