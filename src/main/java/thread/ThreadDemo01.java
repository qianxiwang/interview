package thread;

/**
 * 线程的实现方式之一：继承Thread类
 */
public class ThreadDemo01 extends Thread {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        //启动线程
        thread1.start();
        thread2.start();

        /**
         * 使用run方法时，所有的代码都运行在main方法中
         *      run方法只是一个普通的方法
         *
         * 使用start方法时，线程的代码是运行在各自的线程中
         *      start方法会创建一个新的子线程并启动
         */
        thread1.run();
        thread2.run();
    }
}

/**
 * 共同点：将希望线程执行的代码放到run方法内，然后通过调用start方法来启动线程
 * 区别：
 *      继承Thread：类是单一继承原则
 *      Runnable是一个接口       推荐多使用接口的方式
 */

class Thread1 extends Thread {
    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}