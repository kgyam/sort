package thread.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * completableFuture  jdk8才加入的异步式编程
 */
public class CompletableFutureSample {


    /**
     * * 提供4个静态方法创建异步操作：
     * * public static CompletableFuture<Void> runAsync(Runnable runnable)
     * * public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
     * * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
     * * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
     * * 没有指定Executor的方法会使用ForkJoinPool.commonPool() 作为它的线程池执行异步代码。
     * * 如果指定线程池，则使用指定的线程池运行。
     * * <p>
     * * runAsync方法不支持返回值。
     * * supplyAsync可以支持返回值。
     */

    //无返回值
    //这里的泛型Void 就是当前不需要指定泛型类型时候使用
    public static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run end ...");
            // return "runAsync"; 写return会报错
        });

        Object obj = future.get(); //null
        System.out.println(obj);

    }


    //有返回值 且必须有
    public static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("supply end ...");
            return "supplyAsync";
        });

        Object obj = future.get();
        System.out.println(obj);
    }


    /**
     * --------------------------------------------------------------------
     */


    /**
     * 当CompletableFuture的计算结果完成，或者抛出异常的时候，可以执行特定的Action
     * public CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action)
     * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
     * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
     * public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn)
     * <p>
     * <p>
     * <p>
     * whenComplete是执行当前任务的线程执行继续执行 whenComplete 的任务。
     * whenCompleteAsync是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行
     */
    public static void whenComplete() {
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync start ...");

            if (new Random().nextBoolean() == true) {
                boolean flag = true;
            } else {
                try {
                    throw new Exception("error");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("runAsync end ...");

        });


        future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void aVoid, Throwable throwable) {
                System.out.println("执行完成");
            }

        });


        future.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable throwable) {
                System.out.println("执行失败");
                return null;
            }
        });

    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //runAsync();
        //supplyAsync();
        whenComplete();
    }
}
