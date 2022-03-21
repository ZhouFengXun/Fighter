package com.fanlan.fighterdemo.thread;

import java.util.concurrent.Semaphore;

/**
 * semaphore一般指信号灯，在Java层面称之为信号量。
 * 顾名思义，在使用场景方面就很容易理解，
 * 比如用于流量控制，车库停车等一系列，就是资源有限，排队等待。
 * semaphore用来控制同时访问特定资源的线程数量，通过协调各个线程，以保证合理的使用资源。
 * 是一个线程同步的辅助类。
 *
 *
 * 创建具有给定数量的许可和非公平公平设置的Semaphore
 * Semaphore(int permits)
 * 使用给定的许可数量和给定的公平设置创建一个Semaphore
 * Semaphore(int permits, boolean fair)
 *
 *从信号量中获取一个许可，阻塞直到一个信号量可用，或者线程被中断。
 * acquire()
 * 从此信号量获取给定数量的许可，阻塞直到所有可用或线程被中断。
 * acquire(int permits)
 *
 * 释放许可，将其返回给信号量
 * release()
 * 释放给定数量的许可，将它们返回给信号量
 * release(int permits)
 *
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //创建具有给定数量的许可和非公平公平设置的Semaphore
        //假如有5位按摩师
        Semaphore semaphore = new Semaphore(5);
        //15个客人服务
        for (int i = 0; i < 15; i++) {
            new Thread(new Task(semaphore)).start();
        }
    }

    static class Task extends Thread {
        Semaphore semaphore;

        public Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {

                System.out.println(Thread.currentThread().getName() + "==客人来到足疗店；" );
                //availablePermits  获取返回此信号量中可用的当前许可数。
                if (semaphore.availablePermits()==0){
                    System.out.println("按摩师不足，请稍微等待。。。");
                }
                //获取令牌
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+"--acquire--客人成功获取技师服务；");
                //假设每一位顾客服务时长
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"--release--按摩师服务完成，客人满意离开");
                //释放令牌
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
