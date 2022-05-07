package practice.topics.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.stream.IntStream;

/**
 * @Author shiLong
 * @Desc 线程池demo
 * @Date 2021/10/7 18:45
 * @Version 1.0
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        String[] arr = new String[10];
        int length = arr.length;
        ExecutorService executorService = new ScheduledThreadPoolExecutor(10);
        for (int i = 0; i < length; i++) {
            arr[i] = String.valueOf(i);
            int finalI = i;
            executorService.execute(() -> {
                try {
                    sth(arr[finalI]);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        CompletableFuture[] completableFutures = IntStream.range(0, 10)
                .boxed()
                .map(i -> CompletableFuture.runAsync(() -> sth(String.valueOf(i))).whenComplete((result, e) -> System.out.println(i + "-->> over...")))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(completableFutures).join();
    }

    public static void sth(String string) {
        System.out.println(string);
    }

}
