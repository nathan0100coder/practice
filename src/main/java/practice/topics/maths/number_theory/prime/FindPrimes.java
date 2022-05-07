package practice.topics.maths.number_theory.prime;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shiLong
 * @date 7/2/2021
 * @desc
 */
public class FindPrimes {
    static int i,k;
    static List<Integer> result;
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int n=10000000;
        List<Integer> primes = getPrimes(n);
        System.out.println();
        for (int k=1;k<=primes.size();k++){
            System.out.print(primes.get(k-1)+" ");
            if (k % 10 == 0) {
                System.out.println("\n");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("the count of primes within "+n+" is:_ "+primes.size());
        System.out.println("用时: "+(end-start)+" 毫秒");

    }
    /**
     * the primes within n
     *
     * @param n 范围
     * @return  素数列表
     */
    static List<Integer> getPrimes(int n) {
        result = new ArrayList<>();
        for (k = 1; k <= n; k++) {
            if (k == 1) continue;
            if (k == 2){
                result.add(k);
                continue;
            }
            for (i=0;result.get(i)<=(int)Math.sqrt(k);i++){
                if (k%result.get(i)==0) {
                    break;
                }
            }
            ////一道拦截!   特例助理解  5   6   13
            if (k%result.get(i)!=0) {
                result.add(k);
            }
        }
        return result;
    }
    //等差数列里面的素数

}
