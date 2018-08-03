package interview.question.printab;

/**
 * Created by xuchengyun on 8/2/18.
 */

public class OrderThreadTest {

    public static void main(String[] args) {

        Object lock = new Object();
        Thread t1 = new Thread(new Output(lock, "A"));
        Thread t2 = new Thread(new Output(lock, "B"));
        t1.start();
        t2.start();


    }
}

class Output implements Runnable {
    private Object lock;
    private String value;

    public Output(Object lock, String value) {
        this.lock = lock;
        this.value = value;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (true) {
                try {
                    System.out.println(value);
                    lock.notifyAll();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }
    }
}
