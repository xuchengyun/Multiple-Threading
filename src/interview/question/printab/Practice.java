package interview.question.printab;

/**
 * Created by xuchengyun on 8/3/18.
 */
public class Practice {

    public static void main(String[] args) {
        Object obj = new Object();
        Thread t1 = new PrintAB(obj, "A");
        Thread t2 = new PrintAB(obj, "B");
        t1.start();
        t2.start();
    }
}


class PrintAB extends Thread {

    private Object lock;
    private String str;

    PrintAB(Object lock, String str) {
        this.lock = lock;
        this.str = str;
    }

    public void run() {
        synchronized(lock) {
            while(true) {
                System.out.println(str);
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
