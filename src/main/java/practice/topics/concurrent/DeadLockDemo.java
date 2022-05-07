package practice.topics.concurrent;
 
/**
 * Created by Hollake on 2019\7\17 0017 22:08.
 */
public class DeadLockDemo {
    static boolean flag = false;
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        Thread t1 = new Thread(){
            public void run(){
                synchronized (a) {//加锁
                    try {
                        Thread.sleep(20);//必须进行睡眠等待，不睡眠等待可以，但是一般不会发生死锁，因为程序执行太快
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (b) {//拿到a锁的情况下还想拿到b锁，这与另外一个线程情况正好相反，
                        // 持有资源，不释放资源，还需要其他线程持有的资源，死锁的条件
                        System.out.println("不会执行到这里");
                    }
                }
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                synchronized (b) {//加锁
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (a) {
                        System.out.println("不会执行到这里");
                    }
                }
            }
        };
        t1.start();
        t2.start();
    }
}