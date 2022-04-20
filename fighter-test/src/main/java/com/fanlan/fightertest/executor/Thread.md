### 计算线程数量

一般多线程执行的任务类型可以分为 CPU 密集型和 I/O 密集型，
根据不同的任务类型，我们计算线程数的方法也不一样。

`CPU 密集型任务：
这种任务消耗的主要是 CPU 资源，可以将线程数设置为 N（CPU 核心数）+1，比 CPU 核心数多出来的一个线程是为了防止线程偶发的缺页中断，或者其它原因导致的任务暂停而带来的影响。一旦任务暂停，CPU 就会处于空闲状态，而在这种情况下多出来的一个线程就可以充分利用 CPU 的空闲时间。

I/O 密集型任务：
这种任务应用起来，系统会用大部分的时间来处理 I/O 交互，而线程在处理 I/O 的时间段内不会占用 CPU 来处理，这时就可以将 CPU 交出给其它线程使用。因此在 I/O 密集型任务的应用中，我们可以多配置一些线程，具体的计算方法是 2N。`

### 执行流程

如果执行线程数小于核心线程数，创建线程执行
如果执行线程数大于核心线程数，将之后进来的线程放到队列中，慢慢消费
如果执行线程数大于核心线程数，并且线程队列也满了，最大线程数释放线程来执行新进来的任务
如果三者都满了，执行拒绝策略，也可以自定义拒绝策略

### 在 ThreadPoolExecutor 类中还有一些重要的方法：

submit - 类似于 execute，但是针对的是有返回值的线程。submit 方法是在 ExecutorService 中声明的方法，在 AbstractExecutorService 就已经有了具体的实现。ThreadPoolExecutor 直接复用 AbstractExecutorService 的 submit 方法。
shutdown - 不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务。
将线程池切换到 SHUTDOWN 状态；
并调用 interruptIdleWorkers 方法请求中断所有空闲的 worker；
最后调用 tryTerminate 尝试结束线程池。
shutdownNow - 立即终止线程池，并尝试打断正在执行的任务，并且清空任务缓存队列，返回尚未执行的任务。与 shutdown 方法类似，不同的地方在于：
设置状态为 STOP；
中断所有工作线程，无论是否是空闲的；
取出阻塞队列中没有被执行的任务并返回。
isShutdown - 调用了 shutdown 或 shutdownNow 方法后，isShutdown 方法就会返回 true。
isTerminaed - 当所有的任务都已关闭后，才表示线程池关闭成功，这时调用 isTerminaed 方法会返回 true。
setCorePoolSize - 设置核心线程数大小。
setMaximumPoolSize - 设置最大线程数大小。
getTaskCount - 线程池已经执行的和未执行的任务总数；
getCompletedTaskCount - 线程池已完成的任务数量，该值小于等于 taskCount；
getLargestPoolSize - 线程池曾经创建过的最大线程数量。通过这个数据可以知道线程池是否满过，也就是达到了 maximumPoolSize；
getPoolSize - 线程池当前的线程数量；
getActiveCount - 当前线程池中正在执行任务的线程数量。