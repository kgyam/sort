package thread;


/**
 * join()方法就是让当前线程等待另外一个线程结束才继续执行
 *
 * join(time)即等待一定时间后 当前线程不再等待继续执行代码
 */
public class JoinMain {

    public JoinMain() {
        super();
    }

    public class JoinRunnable implements Runnable {

        private String name;

        public JoinRunnable(String name) {
            this.name = name;
        }


        @Override
        public void run() {

            try {
                Thread.sleep(2000);
                // \t为 8个空格
                System.out.println("JoinRunnable_" + this.name + "\t is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        JoinMain.JoinRunnable r1 = new JoinMain().new JoinRunnable("t");
        Thread t1 = new Thread(r1);
        t1.start();
        t1.join();
//        t1.join(1000);
        System.out.println("main end");
    }
}
