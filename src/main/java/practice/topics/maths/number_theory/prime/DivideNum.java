package practice.topics.maths.number_theory.prime;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shiLong
 * @date 8/3/2021
 * @desc 质因子分解
 */
public class DivideNum {
    static int i, j, k, mid, max;
    public static void main(String[] args) {
        System.out.println(divide_num(728));
    }
    static int the_first_factor(int n) {
        if (n == 1 || n == 2) return n;
        for (i = 2; i <= (int) Math.sqrt(n); i++)
            if (n % i == 0)
                return i;
        return n;
    }

    static List<Integer> get_factors(int n) {
        List<Integer> factors = new ArrayList<>();
        if (the_first_factor(n) == n) factors.add(n);
        while (the_first_factor(n) != n) {
            factors.add(the_first_factor(n));
            n = n / the_first_factor(n);
        }
        factors.add(n);
        return factors;
    }

    static String divide_num(int n) {
        StringBuilder result = new StringBuilder(n +"=");
        List<Integer> factors = get_factors(n);
        for (i=0;i<factors.size();i++){
            if (i<factors.size()-1){
                result.append(factors.get(i)).append(" X ");
            }else {
                result.append(factors.get(i));
            }
        }
        return result.toString();
    }
}
