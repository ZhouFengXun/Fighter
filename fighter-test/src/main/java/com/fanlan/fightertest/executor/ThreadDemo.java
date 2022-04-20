package com.fanlan.fightertest.executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo {


    public static class CustomRejectionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(r.toString() + "被拒绝了，执行入库操作，之后手动补偿");
        }
    }

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) throws Exception {
        CustomRejectionHandler customRejectionHandler = new CustomRejectionHandler();
        ExecutorService executorService = new ThreadPoolExecutor(
                5,
                5,
                0,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(10), customRejectionHandler);
        for (int i = 0; i < 20; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("第" + atomicInteger.getAndIncrement() + "个任务被执行");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
