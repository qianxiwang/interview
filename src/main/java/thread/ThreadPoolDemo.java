package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池的作用：一个请求来到server，server就会开始一个线程来进行处理
 * 1）创建线程
 * 2）在线程中执行业务逻辑
 * 3）销毁线程
 * <p>
 * ==>线程池：在应用启动的时候给你准备好了一些线程，你需要的时候直接拿来用，并不需要去创建
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
//        Executors.newSingleThreadExecutor();
//        Executors.newFixedThreadPool(2);
//        Executors.newCachedThreadPool();
//        Executors.newScheduledThreadPool(3);

        ExecutorService pool = Executors.newCachedThreadPool();

        Thread t1 = new PKThread();
        Thread t2 = new PKThread();
        Thread t3 = new PKThread();
        Thread t4 = new PKThread();

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);

        pool.shutdown();
    }
}
