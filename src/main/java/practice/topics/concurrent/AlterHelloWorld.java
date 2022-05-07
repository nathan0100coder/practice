package practice.topics.concurrent;

import java.util.LinkedList;
 
/**
 * Created by Hollake on 2019\7\17 0017 22:08.
 */
public class AlterHelloWorld {
    static boolean flag = false;
    static String str = "HELLO WORLD";
    static int max = str.length();
    public static void main(String[] args) {
        Object LOCK = new Object();
        LinkedList<String> list = new LinkedList<>();
 
        for (int i = 0; i < max; i++) {
            list.add(String.valueOf(str.charAt(i)));
        }
        Thread t1 = new Thread(){
            public void run(){
                while ( max > 0 ) {
                    synchronized (list) {//加锁
                        while ( flag ) {
                            try {
                                list.wait();//满足条件就释放锁
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //在这里为什儿要进行list是否为null的判断呢，仔细思考一下，如果不判断，例如打印两个字母ab，
                        // 第一次线程t1打印a后，继续到try中，调用list.wait()方法，释放锁，线程t2拿到锁，继续打印b，
                        // 接着同样的释放锁，线程t1从try中的wait()方法中被唤醒，继续执行，但是list中已经没有了元素，
                        // 所以调用pop()方法直接会抛出空指针异常
                        if (list.size() > 0) {
                            System.out.println(Thread.currentThread().getName() + "---" + list.pop());
                        }
                        max--;
                        flag = true;
                        list.notify();//唤醒t2线程，只有当前线程释放锁后才能被唤醒
                    }
                }
 
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                while ( max > 0 ) {
                    synchronized (list) {//加锁
                        while ( !flag ) {
                            try {
                                list.wait();//释放锁等待被唤醒
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (list.size() > 0) {
                            System.out.println(Thread.currentThread().getName() + "---" + list.pop());
                        }
                        max--;
                        flag = false;
                        list.notify();//唤醒t1线程，只有当前线程释放锁后才能被唤醒
                    }
                }
            }
        };
        t1.start();
        t2.start();
    }
}