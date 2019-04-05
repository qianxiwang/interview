package thread;

/**
 * 线程的实现方式之二：实现Runnable接口
 */
public class ThreadDemo02 {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        Thread thread3 = new Thread(new Thread3());
        Thread thread4 = new Thread(new Thread4());

        //启动线程
        thread3.start();
        thread4.start();

    }
}


class Thread3 implements Runnable {

    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}

class Thread4 implements Runnable {

    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}