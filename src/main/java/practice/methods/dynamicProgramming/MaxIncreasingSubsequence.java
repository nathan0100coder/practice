package practice.methods.dynamicProgramming;

/**
 * @author shiLong
 * @date 7/29/2021
 * @desc 最长上升子序列
 */
public class MaxIncreasingSubsequence {
    public static void main(String[] args) {
        int[] arr={-3,2,-8,1,2,3,4,9,6,-2};
        System.out.println(longestIncreasingSubsequence(arr));
    }
    static int longestIncreasingSubsequence(int[] nums) {
        // dp[i]表示以nums[i]为结尾的最长上升子序列的长度
        int []dp = new int[nums.length];
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            // 初始值为1
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    // 若nums[j] < nums[i]那么可以接在该序列后，更新状态
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 记录所有状态中的最大值
            if (dp[i] > ans) {
                ans = dp[i];
            }
        }
        return ans;
    }
}
