package thread;

/**
 * 生产者和消费者：wait和notifyAll的使用（必须在synchronized方法中使用）
 */
public class ProducerConsumerDemo {

    public static void main(String[] args) {
        Person person = new Person();

        new Thread(new Producer(person)).start();
        new Thread(new Consumer(person)).start();
    }
}


class Producer implements Runnable {

    private Person person;

    public Producer(Person person) {
        this.person = person;
    }

    public void run() {

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                this.person.set("小队长", "男");
            } else {
                this.person.set("呼呼", "女");
            }

        }
    }
}


class Consumer implements Runnable {

    private Person person;

    public Consumer(Person person) {
        this.person = person;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            this.person.get();
        }
    }
}