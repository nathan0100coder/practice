package practice.topics.maths.calculations;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author shiLong
 * @date 7/15/2021
 * @desc
 */
public class Sqrt {
    public static void main(String[] args) {
        System.out.println(sqrt(2, 1e-4));

    }
    private static double sqrt(int t, Double precise) {

        if (t < 0) {
            throw new RuntimeException("Negative number cannot have a sqrt root.");
        }

        int i = 0;
        for ( ; i <= t; i++) {
            if (i * i == t) {
                return i;
            }
            if(i * i > t){
                break;
            }
        }
        double low = i-1, high = i, prec = precise != null ? precise : 1e-7;
        double middle, squre;
        while (high - low > prec) {
            middle = (low + high) / 2;
            squre = middle * middle;

            if (squre > t) {
                high = middle;
            } else {
                low = middle;
            }
        }
        return (low + high) / 2;

    }
    static double binary_search_sqrt(double a, int scale) {
        double tmp = helper(a);
        String s = new BigDecimal(tmp).setScale(scale, RoundingMode.HALF_UP).toString();
        return Double.valueOf(s);
    }
    static double helper(double a) {
        double left = 0;
        double right = a;
        while (left <= right) {
            double X = (right + left) / 2;
            if (Math.abs(X * X - a) < 1e-10) return X;
            if (X * X > a) {
                right = X;
            } else {
                left = X;
            }
        }
        return -1;
    }
    //牛顿法开平方
    //a的平方根:  Xn+1=1/2 (Xn+a/Xn)
    static double sqrt(double a,int scale){
        double err=1e-10;
        double solution=a;
        while (Math.abs(solution*solution-a)>=1e-10){
            solution=(solution+a/solution)/2;
        }
        return Double.valueOf(new BigDecimal(solution).setScale(scale, RoundingMode.HALF_UP).toString());
    }
}
