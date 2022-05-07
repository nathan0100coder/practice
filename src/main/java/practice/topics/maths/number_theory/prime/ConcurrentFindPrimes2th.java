package practice.topics.maths.number_theory.prime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentFindPrimes2th {
    // 素数范围 0 - NUM_END
    private static AtomicInteger NUM_END = new AtomicInteger(1000);
    // 最大并发数
    private static final int CPU_CORE_NUM = 32;
    // 线程数
    private static int THREAD_NUM = 10;
    // 对素数计数
    private static AtomicInteger COUNT = new AtomicInteger();
 
    public static void main(String[] args) throws InterruptedException {
        get_primes();
    }
    static void get_primes(){
        // 创建一个定长的线程池 控制线程的最大并发数 超出的线程会在队列中等待
        ExecutorService service = Executors.newFixedThreadPool(CPU_CORE_NUM);
        // 用于批量添加线程
        List<Callable<Integer>> callableList = new ArrayList<Callable<Integer>>();
        // 创建线程
        for (int i = 0; i < THREAD_NUM; i++) {
            final int sign = i;
            // 直接使用匿名内部类
            callableList.add(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    while (NUM_END.get() != 0) {

                        int temp = NUM_END.getAndDecrement();

                        // 打印空内容不能去掉
                        System.out.print("");

                        if (isPrime(temp)) {
                            COUNT.getAndIncrement();
                        }
                    }
                    return COUNT.get();
                }
            });
        }
        try {
            service.invokeAll(callableList, 10000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 及时关闭
        service.shutdown();
        System.out.println("素数个数：" + COUNT.get());
    }
 
    // 判断是否为素数
    static boolean isPrime(int x) {
        int i;
        if (x <= 1)
            return false;
        for (i = 2; i < x; i++) {
            if ((x % i == 0) && (i != x))
                return false;
        }
        return true;
    }
 
}