package practice.topics.maths.number_theory.prime;
 
/**
 * 启动类
 */
public class SequentialPrimeFinder extends AbstractPrimeFinder {
    /**
     * 实现父类中的方法
     * @param number
     * @return
     */
    @Override
    public int countPrimes(final int number) {
        return countPrimesInRange(1,number);
    }
    /**
     * 程序主方法
     * @param args
     */
    public static void main(String[] args){
        //计算的区间为1到10000000
        new SequentialPrimeFinder().timeAndCompute(1000);
    }
}