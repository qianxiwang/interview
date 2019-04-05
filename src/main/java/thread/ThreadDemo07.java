package thread;

public class ThreadDemo07 {
    public static void main(String[] args) {

        //这是两个线程操作同一个对象的synchronized方法，是不影响的
        Example example = new Example();

        Runnable runnable = new ExampleThread(example);

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();


        //两个不同的线程同时访问一个对象的不同synchronized方法，是不影响的
        //如果此时在synchronized方法上加static，那么也是不影响的

        //synchronized修饰的普通方法：对对象加锁
        //static修饰的方法：对类加锁
//        Example example = new Example();
//
//        Runnable runnable1 = new ExampleThread(example);
//        Runnable runnable2 = new ExampleThread2(example);
//
//        Thread thread1 = new Thread(runnable1);
//        Thread thread2 = new Thread(runnable2);
//        thread1.start();
//        thread2.start();

        //这是两个线程分别操作两个独立的对象，是不影响的
//        Runnable runnable1 = new ExampleThread(new Example());
//        Runnable runnable2 = new ExampleThread(new Example());
//
//        Thread thread1 = new Thread(runnable1);
//        Thread thread2 = new Thread(runnable2);
//        thread1.start();
//        thread2.start();
    }
}


class Example {

    /**
     * synchronized (object)：对该object对象上锁
     */
    public void executor() {
        for (int i = 0; i < 10; i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + " Example.executor:" + i);

            synchronized (this) { //this代表当前对象
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " Example.executor:" + i);
            }
        }
    }

//    public synchronized void executor1() {
//        for (int i = 0; i < 10; i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + " Example.executor:" + i);
//        }
//    }

    public synchronized void executor2() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " Example.executor:" + i);
        }
    }
}


class ExampleThread implements Runnable {

    private Example example;

    public ExampleThread(Example example) {
        this.example = example;

    }

    public void run() {
//        example.executor1();
        example.executor();
    }
}

class ExampleThread2 implements Runnable {

    private Example example;

    public ExampleThread2(Example example) {
        this.example = example;

    }

    public void run() {
        example.executor2();
    }
}