package practice.topics.maths.number_theory.prime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author nathan
 */
public class ThreadsFindPrimes {
    static final int NUM =2;
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        int n=10000000;
         findPrimes(n).forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("用时: "+(end-start)+" 毫秒");
    }

    static List<Integer> findPrimes(int n) throws InterruptedException {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();
        int process = Runtime.getRuntime().availableProcessors();
        for (int i = 1; i <= process; i++) {
            Thread thread;

            if (i == process) {
                thread = new Thread(new MyThread(list, n/ process * (i - 1) + 1, n));
            }
            else {
                thread = new Thread(new MyThread(list, n / process * (i - 1) + 1, n / process * i));
            }
            thread.start();
//            thread.join();
            threads.add(thread);
        }


        for (Thread thread : threads) {
            try {
                //join
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    static class MyThread implements Runnable {
        private final List<Integer> list;
        private final int start;
        private final int end;

        public MyThread(List<Integer> list, int start, int end) {
            this.list = list;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                if (i < NUM) {
                    continue;
                }
                int sqrt = (int) Math.sqrt(i);
                int d;
                for (d = NUM; d <= sqrt; ++d) {
                    if (i % d == 0) {
                        break;
                    }
                }
                if (d > sqrt) {
                    list.add(i);
                }
            }
        }
    }
}
