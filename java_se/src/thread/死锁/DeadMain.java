package thread.死锁;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadMain {

    private int count=0;

    private static final Object LOCK_A=new Object();

    private static final Object LOCK_B=new Object();

    public DeadMain(){}


    private void add(){
        synchronized (LOCK_A){
            this.count++;
            synchronized (LOCK_B){
                this.count++;
            }
        }

    }

    private void sub(){
        synchronized (LOCK_B){
            this.count--;
            synchronized (LOCK_A){
                this.count--;

            }
        }

    }



    public  class ThreadA extends Thread{
            private DeadMain deadMain;

        public ThreadA(DeadMain deadMain){
            this.deadMain=deadMain;
        }

        @Override
        public void run() {
            for(int i=0;i<100;i++){
                deadMain.add();
            }
        }
    }



    public  class ThreadS extends Thread{
        private DeadMain deadMain;

        public ThreadS(DeadMain deadMain){
            this.deadMain=deadMain;
        }


        @Override
        public void run() {
            for(int i=0;i<100;i++){
                deadMain.sub();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        System.out.println("main  start");
        DeadMain deadMain=new DeadMain();
        DeadMain.ThreadA threadA=deadMain.new ThreadA(deadMain);
        DeadMain.ThreadS threadS=deadMain.new ThreadS(deadMain);

        threadA.start();
        threadS.start();
        threadA.join();
        threadS.join();

        System.out.println("main  end");
    }
}


