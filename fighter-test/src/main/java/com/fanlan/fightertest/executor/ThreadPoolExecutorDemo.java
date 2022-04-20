package com.fanlan.fightertest.executor;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        //Thread();
        ThreadPool();
    }

    public static void Thread() {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("遍历" + i);
            }
        }).start();
    }

    public static void ThreadPool() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        /**
         * 自定义线程工厂设置线程名
         */
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("ThreadPoolExecutor").build();

        /**
         * Ali开发手册推荐使用ThreadPoolExecutor
         */
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                //核心线程数
                5,
                //最大线程数
                5,
                //线程保存活动时间
                0,
                //时间单位
                TimeUnit.SECONDS,
                /**
                 * 等待执行的任务队列
                 * ArrayBlockingQueue
                 *          - 有界阻塞队列。此队列是基于数组的先进先出队列（FIFO）。此队列创建时必须指定大小
                 * LinkedBlockingQueue
                 *          - 无界阻塞队列。此队列是基于链表的先进先出队列（FIFO）。
                 *          如果创建时没有指定此队列大小，则默认为 Integer.MAX_VALUE。
                 *          吞吐量通常要高于 ArrayBlockingQueue。
                 *          使用 LinkedBlockingQueue
                 *          意味着： maximumPoolSize 将不起作用，线程池能创建的最大线程数为 corePoolSize，因为任务等待队列是无界队列。
                 *          Executors.newFixedThreadPool 使用了这个队列。
                 * SynchronousQueue
                 *          - 不会保存提交的任务，而是将直接新建一个线程来执行新来的任务。
                 *           每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态。
                 *           吞吐量通常要高于 LinkedBlockingQueue。
                 *           Executors.newCachedThreadPool 使用了这个队列。
                 * PriorityBlockingQueue - 具有优先级的无界阻塞队列。
                 */
                new LinkedBlockingDeque<>(10),
                //线程工厂
                threadFactory,
                /**
                 * 拒绝策略（4种）
                 * 当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。线程池支持以下策略：
                 * AbortPolicy - 丢弃任务并抛出异常。这也是默认策略。
                 * DiscardPolicy - 丢弃任务，但不抛出异常。
                 * DiscardOldestPolicy - 丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）。
                 * CallerRunsPolicy - 直接调用 run 方法并且阻塞执行。
                 * 如果以上策略都不能满足需要，也可以通过实现 RejectedExecutionHandler 接口来定制处理策略。如记录日志或持久化不能处理的任务。
                 */
                //new ThreadPoolExecutor.AbortPolicy()
                new CustomRejectionHandler()
        );
        // 执行  runnable 无参无返回值
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(() -> {

                System.out.println(Thread.currentThread().getName() + "  :  "
                        + Thread.currentThread().getState() + " : "
                        + Thread.activeCount() + "  :  "
                        + Thread.currentThread().isAlive() + " : "
                        + Thread.currentThread().getThreadGroup() + "  :  "
                        + Thread.currentThread().getStackTrace()
                );

                System.out.println("第" + atomicInteger.getAndIncrement() + "个任务被执行");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        /**
         *shutdown - 不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务。
         */
        threadPoolExecutor.shutdown();
        /**
         * 调用过shuudown 才会返回true
         */
        boolean shutdown = threadPoolExecutor.isShutdown();
        System.out.println(shutdown);
    }

    public static class CustomRejectionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(r.toString() + "被拒绝了，执行入库操作，之后手动补偿");
        }
    }
}
