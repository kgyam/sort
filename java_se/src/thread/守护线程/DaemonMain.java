package thread.守护线程;

/**
 * 1.守护线程是为其他线程服务的线程
 *
 * 2.守护线程setDaemon(true),就是当所有非守护线程结束后，jvm退出,守护线程也就结束。
 *      因为没有了被守护者，守护线程也就没有工作可做了，也就没有继续运行程序的必要了
 *
 * 3.setDaemon(true)要在start()调用前执行 否则无效
 *
 * 4.守护线程不能持有资源（打开文件等io操作）
 *     因为当守护线程关闭时，守护线程来不及释放资源，会造成数据丢失
 *
 */
public class DaemonMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        DaemonThread r1=new DaemonThread("curry");
        Thread thread=new Thread(r1);
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000);
        System.out.println("main end");
    }

}
