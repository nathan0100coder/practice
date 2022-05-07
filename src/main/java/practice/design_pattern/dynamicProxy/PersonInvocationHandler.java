package practice.design_pattern.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;


public class PersonInvocationHandler<T> implements InvocationHandler {
    /**
    * 被代理对象引用，invoke 方法里面method 需要使用这个 被代理对象
    */
   T target;
   public PersonInvocationHandler(T target) {
       this.target = target;
   }
   /**
    *
    * @param proxy  动态代理对象实例
    * @param method 代表被调用委托类的接口方法，和生成的代理类实例调用的接口方法是一致的，它对应的Method 实例
    * @param args   代表调用接口方法对应的Object参数数组，如果接口是无参，则为null； 对于原始数据类型返回的他的包装类型
    * @return
    * @throws Throwable
    */
   @Override
   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       /**
        * 在转调具体目标对象之前，可以执行一些功能处理
        */
       System.out.println(
               "被动态代理类回调执行, 代理类 proxyClass = "
               +proxy.getClass()
               +" 方法名: "
                       + method.getName()
               + "方法. 方法返回类型："
                       +method.getReturnType()
       +" 接口方法入参数组: "
               +(args ==null ? "null" : Arrays.toString(args)));

       /**
        * 代理过程中插入监测方法,计算该方法耗时
        */
       MonitorUtil.start();
       Thread.sleep(1);
       Object result = method.invoke(target, args);
       MonitorUtil.finish(method.getName());
       return result;
   }
}