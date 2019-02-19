package thread.wait和notify;

import java.util.LinkedList;
import java.util.Queue;


/**
 * wait && notify 用于多线程的协调运行
 *
 * 1.在synchronized内部可以调用wait()使得线程进入等待状态
 * 2.必须在已获得的锁对象上调用wait() 否则会抛出IllegalMonitorStateException异常
 * 3.在synchronized内部可以调用notify()/notifyAll()唤醒其他等待线程
 * 4.必须在已获得的锁对象上调用notify()/notifyAll() 否则会抛出IllegalMonitorStateException异常
 */
public class TestQueue {

    private Queue<String> queue = new LinkedList<>();


    public synchronized String getTask() throws InterruptedException {

        while (queue.isEmpty()) {
            this.wait();
        }
        return queue.remove();
    }

    public synchronized void addTask(String taskName) {
        this.queue.add(taskName);
        this.notifyAll();
    }


    public class ThreadAdd extends Thread {
        private TestQueue testQueue;

        public ThreadAdd(TestQueue testQueue) {
            this.testQueue = testQueue;
        }

        @Override
        public void run() {

            while (!this.isInterrupted()) {
                try {
                    String name;
                    name = testQueue.getTask();
                    System.out.println("hello, " + name);
                } catch (InterruptedException e) {
                    System.out.println("isInterrupted!!!!!!!!!");
                    e.printStackTrace();
                    break;
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        TestQueue queue = new TestQueue();
        ThreadAdd t1 = queue.new ThreadAdd(queue);
        t1.start();
        queue.addTask("tommy");
        Thread.sleep(1000);
        queue.addTask("kate");
        Thread.sleep(1000);
        queue.addTask("kid");
        Thread.sleep(1000);
        t1.interrupt();
        t1.join();


        System.out.println("main end");


    }

}
