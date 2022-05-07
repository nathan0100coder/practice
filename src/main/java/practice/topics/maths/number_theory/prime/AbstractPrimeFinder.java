package practice.topics.maths.number_theory.prime;
 
/**
 * 此类作为工具类
 */
public abstract class AbstractPrimeFinder {
    /**
     * 判断一个数是否为素数
     * @param number
     * @return
     */
    public boolean isPrime(final int number){
        if(number <= 1) return false;
        for(int i=2;i<=Math.sqrt(number);i++)
            if(number % i==0) return false;
            return true;
    }
 
    /**
     * 计算一个区间内的素数的个数
     * @param lower
     * @param upper
     * @return
     */
    public int countPrimesInRange(final int lower,final int upper){
        int total=0;
        for (int i=lower;i<=upper;i++)
            if(isPrime(i)) total++;
        return total;
    }
 
    /**
     * 统计程序计算素数所花费的时间
     * @param number
     */
    public void timeAndCompute(final int number){
        final long start = System.nanoTime();
        final long numberOfPrimes=countPrimes(number);
        final long end = System.nanoTime();
        System.out.printf("Number of primes under %d is %d\n",number,numberOfPrimes);
        System.out.println("Time (seconds) taken is:"+(end-start)/1.0e9);
    }
    public abstract int countPrimes(final int number);
}