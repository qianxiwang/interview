package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Person2 {

    private String name;
    private String sex;

    private Boolean isEmpty = true;

    private ReentrantLock lock = new ReentrantLock(); //创建可重入锁对象
    private Condition condition = lock.newCondition();

    //生产
    public void set(String name, String sex) {

        lock.lock();

        while (!isEmpty) { //不为空，生产者应该停下来，等待消费者去消费
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.name = name;

        try {
            Thread.sleep(500);

            this.sex = sex;

            System.out.println("生产：" + getName() + ":" + getSex());

            isEmpty = false;//不为空
            condition.signal(); //唤醒消费者，起来消费了
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    //消费
    public void get() {

        lock.lock();

        while (isEmpty) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println("消费：" + getName() + ":" + getSex());

            isEmpty = true;
            condition.signal();//唤醒生产者，应该生产了
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
