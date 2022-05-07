package practice.topics.maths.calculations;

/**
 * @author shiLong
 * @date 7/24/2021
 * @desc
 */
public class Pi {
    static double pi, i, j, tmp;
    static boolean flag;

    public static void main(String[] args) {
        calculatePi();
    }

    static void calculatePi() {
        pi = 0;
        flag = true;
        tmp = (double) 1 / 10000000;
        for (i = 1, j = 1; flag; i = i + 2, j++) {
            if ((double) 1 / i > tmp) {
                if (j % 2 == 1) pi = pi + 1 / i;
                else pi = pi - 1 / i;
            } else flag = false;
        }
        System.out.println(pi * 4);
    }
}
