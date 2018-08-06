package interview.question.printabc;

/**
 * Created by xuchengyun on 8/3/18.
 */
public class PrintABC {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        Object d = new Object();

        Thread ta = new Thread(new PrintThread("A", d, a));
        Thread tb = new Thread(new PrintThread("B", a, b));
        Thread tc = new Thread(new PrintThread("C", b, c));
        Thread td = new Thread(new PrintThread("D", c, d));

        ta.start();
        tc.start();
        tb.start();
        td.start();



    }
}

class PrintThread implements Runnable{

    private String name;
    private Object pre;
    private Object self;

    public PrintThread(String name, Object pre, Object self) {
        this.name = name;
        this.pre = pre;
        this.self = self;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (pre) {
                synchronized (self) {
                    System.out.println(name);
                    self.notify();
                }
                try {
                    pre.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
