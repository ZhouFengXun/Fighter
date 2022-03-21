package com.fanlan.fighterdemo.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 核心关注方法只有两个，latch.countDown();和latch.await();
 * countDown() 方法每次调用都会将 state 减 1，
 * 直到state 的值为 0；而 await 是一个阻塞方法，
 * 当 state 减 为 0 的时候，await 方法才会返回。await 可以被多个线程调用，
 *所有调用了await 方法的线程阻塞在 AQS 的阻塞队列中，等待条件满（state == 0），将线程从队列中一个个唤醒过来。
 */
public class CountdownLatchDemo {
    public static void main(String[] args) {
        //老板需要等待15个员工会议室开会
        final CountDownLatch latch = new CountDownLatch(15);
        for (int i = 0; i < 15; i++) {
            Random random = new Random();
            final int timer = random.nextInt(1000);
            new Thread(() -> {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在赶路");
                    //模仿每个员工走自己线程需要的时间
                    Thread.sleep(1000 + timer);
                    //调用latch的countDown方法使计数器-1；一共15个
                    latch.countDown();
                    System.out.println("子线程" + Thread.currentThread().getName() + "到会议室了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        try {
            System.out.println("领导等待员工会议室开会...");
            //主线程阻塞等待计数器归零
            latch.await();
            System.out.println("员工都来了,会议开始");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
