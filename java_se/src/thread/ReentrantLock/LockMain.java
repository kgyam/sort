package thread.ReentrantLock;

public class LockMain {


    public LockMain(){}

    public class ThreadAdd extends Thread {

        private Counter counter;

        public ThreadAdd(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for(int i=0;i<1000;i++){
                counter.add();
            }
        }
    }




    public class ThreadSub extends Thread{
        private Counter counter;

        public ThreadSub(Counter counter){
            this.counter=counter;
        }

        @Override
        public void run() {
            for(int i=0;i<1000;i++){
                counter.sub();
            }
        }
    }


    public static void main(String[] args) {
        Counter counter=new Counter();

        ThreadAdd threadAdd=new LockMain().new ThreadAdd(counter);
        ThreadSub threadSub=new LockMain().new ThreadSub(counter);

        threadAdd.start();
        threadSub.start();

        System.out.println("counter的count为： "+counter.getCount());
        System.out.println("main end");
    }
}
