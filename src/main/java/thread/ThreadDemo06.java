package thread;

/**
 * 多线程在操作共享数据或者是操作数据的线程代码有多行，如果有其他线程也参与了运算，
 * 就会导致线程的安全问题
 * <p>
 * 锁:
 * 1)synchronized关键字来完成对对象的加锁功能
 */
public class ThreadDemo06 {

    public static void main(String[] args) {

        Bank bank = new Bank();

        Runnable runnable = new MoneyThread(bank);

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();
    }
}


class Bank {
    private int money = 1000;

    public synchronized int get(int number) {
        if (number < 0) {
            System.out.println("不能取负数的钱");
            return -1;
        } else if (number > money) {
            System.out.println("余额不足1");
            return -2;
        } else if (money < 0) {
            System.out.println("余额不足2");
            return -3;
        } else {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            money -= number;
            System.out.println("还剩：" + money);

            return number;
        }
    }
}


class MoneyThread implements Runnable {

    private Bank bank;

    public MoneyThread(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        bank.get(800);
    }
}

