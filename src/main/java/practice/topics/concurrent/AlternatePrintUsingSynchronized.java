//package practice.topics.concurrent;
//
//import org.testng.annotations.Test;
//
///**
// * @author liming
// * @date 2020/10/14
// * @description 交替打印 A1B2C3 ...
// */
//
//public class AlternatePrintUsingSynchronized {
//
//    static Thread t1 = null, t2 = null;
//
//    /**
//     * 使用 synchronized
//     */
//    @Test
//    public void alternatePrint() {
//        Object lock = new Object();
//        char[] aI = "1234567".toCharArray();
//        char[] aC = "ABCDEFG".toCharArray();
//
//        t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < aC.length; i++) {
//                    synchronized (lock) {
//                        System.out.println(aC[i]);
//                        lock.notify();
//                        try {
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        });
//
//        t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < aI.length; i++) {
//                    synchronized (lock) {
//                        System.out.println(aI[i]);
//                        lock.notify();
//                        try {
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        });
//
//        t1.start();
//        t2.start();
//    }
//
//}
