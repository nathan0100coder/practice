package practice.methods.recursion;

/**
 * @author shiLong
 * @date 7/2/2021
 * @desc
 */
public class FibonacciNum {
    public static void main(String[] args) {
        //1 1 2 3 5 8 13
        System.out.println(nthFib02(70,1,1));//13
    }

    /**
     * common recursion
     * @param n
     * @return
     */
    private static int nthFib(int n){
        return n<3?1:nthFib(n-1)+nthFib(n- 2);
    }

    /**
     * tail recursion
     * @param n
     * @param ret1
     * @param ret2
     * @return
     */
    private static int nthFib02(int n,int ret1,int ret2){
        return n==1?ret1: nthFib02(n-1,ret2,ret1+ret2);
    }

}

