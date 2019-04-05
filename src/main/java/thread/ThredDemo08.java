package thread;

/**
 * 作业：为什么是world，hello，中间到底隔了多久才输出
 */
public class ThredDemo08 {

    public static void main(String[] args) {

        C c = new C();
        Thread t1 = new T1(c);
        Thread t2 = new T2(c);

        t1.start();

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        t2.start();

    }
}


class C {
    public synchronized static void hello() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }

    public synchronized void world() {
        System.out.println("world");
    }
}

class T1 extends Thread {

    public C c;

    public T1(C c) {
        this.c = c;
    }

    @Override
    public void run() {
        c.hello();
    }
}

class T2 extends Thread {

    public C c;

    public T2(C c) {
        this.c = c;
    }

    @Override
    public void run() {
        c.world();
    }
}