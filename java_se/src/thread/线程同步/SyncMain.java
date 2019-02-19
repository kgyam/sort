package thread.线程同步;

/**
 * 线程同步：用synchronized方式同步
 *
 * count++ 或者 count-- 这种操作符并不是原子操作
 * 因为栈内存要先 1.先 iload (将变量压入操作数栈)， 2.再 iadd(在操作数栈中进行值相加)， 3.最后 istore(弹出操作数栈，保存到变量表)
 *
 *
 */
public class SyncMain {

    public static int count = 0;
    public static final Object LOCK=new Object();


    public static void add(){

        count++;
        System.out.println(count);

    }



    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");

          int[] sum = {0};

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    synchronized (LOCK){
                        //count++;
                        sum[0]++;
                    }

                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    synchronized (LOCK){
                        //count--;
                        sum[0]--;
                    }

                }
            }
        };


        t1.start();
        t2.start();
        t1.join();
        t2.join();

//        System.out.println("count= "+count);
        System.out.println("sum="+sum[0]);
    }


}
