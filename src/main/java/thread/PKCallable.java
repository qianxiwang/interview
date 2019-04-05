package thread;

import java.util.concurrent.Callable;

public class PKCallable implements Callable {
    public Object call() throws Exception {

        String value = "我是PK";

        System.out.println("准备开始执行复杂的业务逻辑...");
        Thread.sleep(5000);
        System.out.println("复杂的业务逻辑执行完毕...");

        return value;
    }
}
