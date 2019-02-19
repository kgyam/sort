package thread.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();


    public void add() {

        lock.lock();
        try {
            count++;//这个操作包含三个指令，所以要同步
        } finally {
            lock.unlock();
        }
    }


    public void sub() {

        lock.lock();
        try {
            count--;
        } finally {
            lock.unlock();
        }

    }




    public Integer getCount(){
        return this.count;
    }
}
