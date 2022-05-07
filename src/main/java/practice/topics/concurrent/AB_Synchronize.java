package practice.topics.concurrent;

public class AB_Synchronize {
    static boolean flag = false;
    public static void main(String[] args) {
        alternate_print_AB();
    }
    static void alternate_print_AB(){
        Object LOCK = new Object();
        Thread t1 = new Thread(){
            public void run(){
                while ( true ) {
                    synchronized (LOCK) {//加锁
                        while ( flag ) {
                            try {
                                LOCK.wait();//满足条件就释放锁
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(Thread.currentThread().getName() + "---" + "A");
                        flag = true;
                        LOCK.notify();//唤醒另外一个线程，只有当前线程释放锁后才能被唤醒
                    }
                }

            }
        };
        Thread t2 = new Thread(){
            public void run(){
                while ( true ) {
                    synchronized (LOCK) {//加锁
                        while ( !flag ) {
                            try {
                                LOCK.wait();//等待释放锁
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(Thread.currentThread().getName() + "---" + "B");
                        flag = false;
                        LOCK.notify();//唤醒生产者线程，只有当前线程释放锁后才能被唤醒
                    }
                }
            }
        };
        t1.start();
        t2.start();
    }
}