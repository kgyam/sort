package thread.中断线程;


/**
 * 中断线程的两种方式：
 * 1.使用t1.interrupt()方法中断t1线程
 * 2.使用标志位 volatile boolean ,修改标志位从而中断线程，使用volatile解决runninng更新延迟（可见性）问题
 *
 */
public class InterruptedThread extends Thread {
    public volatile boolean runnning=true;


    @Override
    public void run() {
        //while (!(Thread.currentThread().isInterrupted())) {
            while (runnning) {
            System.out.println("InterruptedThread is runninng");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("InterruptedThread is interrupted!");
//                e.printStackTrace();
                break;
            }
        }
        System.out.println("Thread end");

    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        InterruptedThread t1 = new InterruptedThread();
        t1.start();
        Thread.sleep(1000);
        //t1.interrupt();
        t1.runnning=false;
        System.out.println("main end");
    }
}
