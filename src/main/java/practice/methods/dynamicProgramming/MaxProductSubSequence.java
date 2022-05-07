package practice.methods.dynamicProgramming;

/**
 * @author shiLong
 * @date 7/25/2021
 * @desc 乘积最大的连续子序列
 */
public class MaxProductSubSequence {
    static int max, imax, imin,i,tmp;

    public static void main(String[] args) {
        int[] ints = {0, -1, 3, -2, 5, 1, -3};
        System.out.println(maxProduct(ints));

    }

    private static int maxProduct(int[] nums) {
        max = Integer.MIN_VALUE;
        imax = 1;
        imin = 1;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }

}
