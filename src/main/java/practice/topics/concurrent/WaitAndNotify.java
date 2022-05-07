package practice.topics.concurrent;

import java.util.LinkedList;

public class WaitAndNotify {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        int MAX = 5;//生产商品总数
        Thread t1 = new Thread(){
            public void run(){
                while ( true ) {
                    synchronized (list) {//加锁
                        while ( list.size() == MAX ) {
                            try {
                                list.wait();//如果生产商品数目大于生产商品最大值，那么释放锁
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        int num = (int) (Math.random() * 100);
                        list.add(num);
                        System.out.println(Thread.currentThread().getName() + "---" + num);
                        list.notify();//唤醒消费者线程，只有当前线程释放锁后才能被唤醒
                    }
                }
 
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                while ( true ) {
                    synchronized (list) {//加锁
                        while ( list.size() == 0 ) {//消费完队列中商品进行等待
                            try {
                                list.wait();//等待释放锁
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        int num = list.pop();//队列中有商品，进行消费
                        System.out.println(Thread.currentThread().getName() + "---" + num);
                        list.notify();//唤醒生产者线程，只有当前线程释放锁后才能被唤醒
                    }
                }
            }
        };
        t1.start();
        t2.start();
    }
}