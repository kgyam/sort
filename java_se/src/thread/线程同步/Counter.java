package thread.线程同步;


/**
 *
 */
public class Counter {

    int count = 0;

    public Counter() {
    }


    public  synchronized void add() {
        this.count++;
    }


    public void sub() {
        synchronized (this){
            this.count--;
        }

    }




    public class ThreadA extends Thread {
        private Counter counter;

        public ThreadA(Counter counter) {
            this.counter = counter;
        }


        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                counter.add();
            }
        }
    }




    public class ThreadS extends Thread {
        private Counter counter;

        public ThreadS(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                counter.sub();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        Counter counter = new Counter();
        ThreadA threadA = counter.new ThreadA(counter);
        ThreadS threadS = counter.new ThreadS(counter);

        threadA.start();
        threadS.start();
        threadA.join();
        threadS.join();
        System.out.println("count= "+counter.count);
        System.out.println("main end");
    }
}
