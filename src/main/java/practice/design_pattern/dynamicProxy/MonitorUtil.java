package practice.design_pattern.dynamicProxy;






public class MonitorUtil {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void start() {
        threadLocal.set(System.currentTimeMillis());
   }


   /**
    * 结束时打印耗时
    * @param methodName 方法名
    */
   public static void finish(String methodName) {
       long finishTime = System.currentTimeMillis();
       System.out.println(methodName + "方法执行耗时" + (finishTime - threadLocal.get()) + "ms");
   }
}