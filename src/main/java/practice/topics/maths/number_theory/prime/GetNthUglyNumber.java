package practice.topics.maths.number_theory.prime;

/**
 * @author shiLong
 * @date 8/12/2021
 * @desc Nth 丑数-----质因数  只包含2 3 5
 */
public class GetNthUglyNumber {
    public static void main(String[] args) {
        int n=10;
        System.out.println(Nth_ugly_num(n));
    }
    static int Nth_ugly_num(int n) {
        if(n <= 0){
            return 0;
        }
        int m = 0;
        int number = 1;
        while(m < n)
        {
            if(isUglyNumber(number)){
                m++;
            }
            number++;
        }
        return number-1;
    }

    static boolean isUglyNumber(int num){
        while(num % 2 == 0) num /= 2;
        while(num % 3 == 0) num /= 3;
        while(num % 5 == 0) num /= 5;
        return num == 1;
    }
}
