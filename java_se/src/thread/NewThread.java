package thread;


/**
 * 1.线程的start()方法不能调用两次，会抛出线程状态异常
 *
 * 2.只有start()方法能够启动一个新线程，直接调用run()方法并不能让jvm启动一个新线程只能按普通方法调用
 * 通过currentThread().getId()获取当前线程id就可以知道，调用run()方法其实并没有启动新的线程
 *
 * 3.Thread.sleep()可以让当前线程暂停
 */
public class NewThread extends Thread {

    private String name;

    public NewThread(String name) {
        this.name = name;

    }

    @Override
    public void run() {
        super.run();
        System.out.println("NewThread_" + this.name + "的id为：" + currentThread().getId());
        for (int i = 0; i < 3; i++) {
            System.out.println("NewThread_" + this.name + "的第" + i + "次循环");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) throws InterruptedException {

        System.out.println("main start");
        System.out.println("main方法的id为：" + currentThread().getId());

        Thread t1 = new NewThread("tommy");
        NewThread t2 = new NewThread("kate");
        t1.start();
        t2.run();

        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.println("main：" + i);

        }


        System.out.println("main end");
    }
}
