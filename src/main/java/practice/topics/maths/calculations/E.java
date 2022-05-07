package practice.topics.maths.calculations;

/**
 * @author shiLong
 * @date 7/18/2021
 * @desc
 */
public class E {
    public static void main(String[] args) {
//        System.out.println(getE01());
        System.out.println(getE02());


    }
    private static double getE01(){
        double e = 0.0;
        for (int i = 20; i > 0; i--) {
            e = (e + 1.0) / i;
        }
        e++;
        return e;

    }
    private static double getE02(){
        double e = 1.0,t = 1.0;
        for (int i = 1; i <= 20; i++) {
            t /= i;
            e += t;
        }
        return e;

    }
}
