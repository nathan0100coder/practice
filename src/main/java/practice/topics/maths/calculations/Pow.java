package practice.topics.maths.calculations;

/**
 * @author shiLong
 * @date 7/15/2021
 * @desc
 */
public class Pow {
    public static void main(String[] args) {
        System.out.println(pow(2, 7));


    }

    private static double pow(double x, int n) {
        double ret = 1.0;
        long i = Math.abs((long) n);
        while (i != 0) {
            if ((i & 1) > 0) {
                ret *= x;
            }
            x *= x;
            i = i >> 1;
        }

        return n < 0 ? 1.0 / ret : ret;
    }
}
