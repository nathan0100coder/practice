package practice.topics.concurrent;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
 
/**
 * Created by Hollake on 2019\8\14 0014 15:31.
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingDeque<Integer> queue = new LinkedBlockingDeque<>(5);
        Thread t1 = new Thread(){
            public void run(){
                while ( true ) {
                    int temp = (int) (Math.random() * 100);
                    try {
                        queue.put(temp);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "----" + temp);
                }
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                while ( true ) {
                    try {
                        int res = queue.take();
                        System.out.println(Thread.currentThread().getName() + "----" + res);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        t2.start();
    }
}