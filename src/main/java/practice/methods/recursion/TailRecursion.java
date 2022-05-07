package practice.methods.recursion;

/**
 * @author shiLong
 * @date 7/15/2021
 * @desc
 */
public class TailRecursion {
    public static void main(String[] args) {
        System.out.println(gcd(16, 12));//4
    }

    private static int factorial(int n){
        if (n==1) return 1;
        return factorial(n-1)*n;
    }

    /**
     * tail
     * total storage the result
     * @param n
     * @param total
     * @return
     */
    private static int factorial(int n,int total){
        if (n==1) return total;
        return factorial(n-1,n*total);
    }

    /**
     * f0 = 0 f1 = 1 f2 = 1...
     * @param n
     * @return
     */
    private static int fibonacci(int n){
        if (n==0||n==1) return n;
        return fibonacci(n-1)+fibonacci(n-2);
    }

    /**
     * tail
     * @param n
     * @param n1
     * @param n2
     * @return
     */
    private static int fibonacci(int n,int n1,int n2){
        if (n==0) return n1;
        return fibonacci(n-1,n2,n1+n2);
    }

    /**
     * 最大公约数
     * @param num1
     * @param num2
     * @return
     */
    private static int gcd(int num1,int num2){
        return num2 == 0?num1:gcd(num2,num1%num2);
    }


    //最大公约数
    private static int gcd2(int a,int b){
        return b == 0? a:gcd2(b,a-b*(a/b));
    }

}
