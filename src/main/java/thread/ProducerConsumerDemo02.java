package thread;

/**
 * 可重入锁的使用
 */
public class ProducerConsumerDemo02 {

    public static void main(String[] args) {
        Person2 person = new Person2();

        new Thread(new Producer2(person)).start();
        new Thread(new Consumer2(person)).start();
    }
}


class Producer2 implements Runnable {

    private Person2 person2;

    public Producer2(Person2 person2) {
        this.person2 = person2;
    }

    public void run() {

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                this.person2.set("小队长", "男");
            } else {
                this.person2.set("呼呼", "女");
            }

        }
    }
}


class Consumer2 implements Runnable {

    private Person2 person2;

    public Consumer2(Person2 person2) {
        this.person2 = person2;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            this.person2.get();
        }
    }
}