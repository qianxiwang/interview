package thread;

import java.util.concurrent.FutureTask;

public class ThreadDemo04 {

    public static void main(String[] args) throws Exception {

        FutureTask<String> futureTask = new FutureTask<String>(new PKCallable());

        new Thread(futureTask).start();//启动线程

        if (!futureTask.isDone()) {
            System.out.println("任务还没有执行完毕，请稍等片刻。");
        }

        System.out.println("任务返回值是：" + futureTask.get());
    }
}
