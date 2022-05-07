package practice.methods.dynamicProgramming;

/**
 * @author shiLong
 * @date 8/14/2021
 * @desc sth
 */
public class CuttingRope {
    public static void main(String[] args) {
        int n=70;
        System.out.println(cuttingRope(n));

    }

    /**
     * 10 输出: 36 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     * @param n
     * @return
     */
    static int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for(int i = 3;i <= n;i++) {
            // 下列循环是为了求 dp[i] 多大 所以每次得出一次 tempMAXJ 都得和dp[i] 比较一次，直到找到 dp[i] 最大值
            for (int j = 2; j < i;j++) {
                int tempMAXJ = Math.max((i-j)*j, j * dp[i-j]); // 当前 剪了 j 的时候较大值 j 可以去2 、3 、4。。。每剪一次就有一种乘积的可能
                dp[i] = Math.max(tempMAXJ, dp[i]);
            }
        }
        return dp[n];
    }
}
