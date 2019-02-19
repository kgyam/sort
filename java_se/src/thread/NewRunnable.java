package thread;


/**
 * 1.用runnable实现线程，要将先实例化runnable接口的实现类，
 *   再实例化Thread类将runnable的实现类放到Thread类里面
 */
public class NewRunnable implements Runnable {
    private String name;

    public NewRunnable(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        System.out.println("当前的线程_"+this.name+"的id为："+Thread.currentThread().getId());
        for (int i = 0; i < 3; i++) {
            System.out.println("NewRunnable_" + this.name + "的第" + i + "次");
        }
    }


    public static void main(String[] args) {
        System.out.println("main start");
        System.out.println("main方法线程的id为："+Thread.currentThread().getId());
        Runnable r1=new NewRunnable("curry");
        Runnable r2=new NewRunnable("kd");
        Thread t1=new Thread(r1);
        t1.start();

        t1=new Thread(r2);
        t1.start();


        System.out.println("main end ");
    }
}
