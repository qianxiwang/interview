package thread;

public class Person {

    private String name;
    private String sex;

    private Boolean isEmpty = true;

    //生产
    public synchronized void set(String name, String sex) {

        while (!isEmpty) { //不为空，生产者应该停下来，等待消费者去消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.name = name;

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.sex = sex;

        System.out.println("生产：" + getName() + ":" + getSex());

        isEmpty = false;//不为空
        this.notifyAll(); //唤醒消费者，起来消费了
    }

    //消费
    public synchronized void get() {
        while (isEmpty) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("消费：" + getName() + ":" + getSex());

        isEmpty = true;
        this.notifyAll();//唤醒生产者，应该生产了
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
