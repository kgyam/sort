package thread.守护线程;

public class DaemonThread implements Runnable{
    private String name;

    public DaemonThread(String name){
        this.name=name;
    }

    @Override
    public void run() {
        System.out.println("DaemonThread_"+this.name+" start");

        while (!(Thread.currentThread().isInterrupted())){
            try {
                Thread.sleep(100);
                System.out.println("running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
