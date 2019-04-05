package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 需求：主线程中获取子线程的返回值
 * 1)主线程等待
 * 2)使用线程的join获取子线程的返回值
 * 3)Callable接口：线程池
 */
public class ThreadDemo05 {

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(new PKCallable());

        if (!future.isDone()) {
            System.out.println("任务还没有执行完毕，请稍等片刻。");
        }

        try {
            System.out.println("任务返回值是：" + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }
}
