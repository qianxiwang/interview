package thread;

/**
 * 需求：主线程中获取子线程的返回值
 * 1)主线程等待
 * 2)使用线程的join获取子线程的返回值
 * 3)Callable接口：FutureTask/线程池
 */
public class ThreadDemo03 implements Runnable {

    private String value;

    public void run() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        value = "我是PK";

    }

    public static void main(String[] args) throws Exception {
        ThreadDemo03 demo = new ThreadDemo03();
        Thread thread = new Thread(demo);

        thread.start();

        /**
         while (demo.value == null) {
         Thread.sleep(100);
         }
         */

        thread.join();


        System.out.println("返回值是：" + demo.value);
    }
}
