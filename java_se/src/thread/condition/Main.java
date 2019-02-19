package thread.condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public Main() {
    }


    public class Result {
        private Queue<String> queue = new LinkedList<>();
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        public void add(String name) {
            lock.lock();
            try {
                queue.add(name);
                condition.signalAll(); //等价于notifyAll()
            } finally {
                lock.unlock();
            }
        }


        public String get() {
            String name="";
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    condition.await();
                }
                name = queue.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return name;
        }

    }


    public class ThreadA extends Thread {

        private Result result;

        public ThreadA(Result result) {
            this.result = result;
        }

        @Override
        public void run() {
            try {
                while (!(Thread.currentThread().isInterrupted())) {
                    System.out.println("running result: " + result.get());
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("catch sth!!!!");
                return;
            }

        }
    }


    public static void main(String[] args) throws InterruptedException {

        System.out.println("main start");
        Main main = new Main();

        Result result = main.new Result();

        ThreadA t1 = main.new ThreadA(result);
        t1.start();
        result.add("tt");
        Thread.sleep(1000);
        result.add("kata");
        Thread.sleep(1000);
        result.add("meta");
        Thread.sleep(1000);
        t1.interrupt();
        t1.join();



        System.out.println("main end");
    }
}
