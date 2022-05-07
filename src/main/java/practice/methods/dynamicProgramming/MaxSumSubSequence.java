package practice.methods.dynamicProgramming;

/**
 * @author shiLong
 * @date 7/25/2021
 * @desc 最大子序和
 */
public class MaxSumSubSequence {
    static int pre,maxAns;
    public static void main(String[] args) {
        int[] ints = {0, -1, 3, -2, 5, 1, -3};
        System.out.println(maxSubArray(ints));//3 -2 5 1
    }
    public static int maxSubArray(int[] nums) {
        pre = 0;
        maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}
