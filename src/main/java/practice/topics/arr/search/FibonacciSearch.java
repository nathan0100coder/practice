package practice.topics.arr.search;

import java.util.Arrays;

/**
 * @author shiLong
 * @date 7/5/2021
 * @desc fibonacciSearch
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = { 11, 12,53,5, 15, 22, 25, 31, 42, 47, 49, 59, 68, 88,107,132};
        System.out.println(Arrays.toString(arr));
        System.out.println(fibonacciSearch(arr,12));
        System.out.println(fibonacciSearch(arr,47));
        System.out.println(fibonacciSearch(arr,31));

    }

    /**
     * fibonacci method :mid = left + fib[k-1]-1
     * @param arr
     * @param target
     * @return
     */
    private static int fibonacciSearch(int[] arr,int target){
        int left = 0;
        int right = arr.length - 1;

        int idx = 0;//idx of fib
        
        int f[] = initFib();

        while (arr.length > f[idx]) {
            idx++;
        }
        int[] newArr = Arrays.copyOf(arr, f[idx]-1);//f(k):newLen

        for (int i = right + 1; i < newArr.length; i++) {
            newArr[i] = arr[right];
        }//Make up

        while (left <= right) {
            int mid = left + f[idx - 1] - 1;

            if (target < newArr[mid]) {
                right = mid - 1;
                idx--;
            } else if (target > newArr[mid]) {
                left = mid + 1;
                idx -= 2;
            }
            else {//
                if (mid <= right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        return -1;
    }

    private static int[] initFib() {
        int maxSize=20;
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
}
