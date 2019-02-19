package thread.读写锁;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 适用条件：同一个实例，有大量线程读取，少量线程修改
 *
 * ReentrantReadWriteLock由一对锁组成，一个是写锁，一个是读锁
 * ReentrantReadWriteLock只允许一个线程写入
 * ReentrantReadWriteLock允许多个线程同时读取，适合读多写少的场景
 *
 * 写锁上锁的时候，是无法获取读锁的
 */
public class Main {

    public Main() {
    }

    public class Result {
        private List<String> list = new ArrayList<>();
        final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        final Lock rlock = lock.readLock();
        final Lock wlock = lock.writeLock();

        public void getResult() {
            rlock.lock();
            try {
                for (String title : list) {
                    System.out.println("the result is : " + title);
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rlock.unlock();
            }

        }


        public void add(String name) {
            wlock.lock();
            try {
                System.out.println("进入add 方法体");
                list.add(name);
            } finally {
                wlock.unlock();
            }
        }

    }


    public class ThreadR extends Thread {

        private Result result;
        private int id;

        public ThreadR(Result result,int id) {
            this.result = result;
            this.id=id;
        }

        @Override
        public void run() {
            try {
                System.out.println("running by id = "+id);
                result.getResult();
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                return;
            }


        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        Main main = new Main();
        Result result = main.new Result();
        result.add("tommy");
        result.add("kate");
        result.add("mike");

        ThreadR t1 = main.new ThreadR(result,1);
        ThreadR t2 = main.new ThreadR(result,2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("main end");

    }

}
