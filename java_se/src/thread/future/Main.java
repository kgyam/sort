package thread.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 实现callable<T>接口 实现有返回值的线程
 *
 * Future<T>表示一个未来可能会返回的结果
 *
 */

public class Main {
    public static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        try {
            Task task = new Task("tommy");
            Future<String> future = pool.submit(task);
            System.out.println("future.get() begin");
            while (!future.isDone()){
                //轮询
                Thread.sleep(100);
            }
            String result = future.get();//可能阻塞
            System.out.println("result:    " + result);
            System.out.println("main end");
        } finally {
            pool.shutdown();
        }

    }
}
