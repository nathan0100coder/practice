package practice.topics.str;

import java.util.Arrays;

/**
 * @author shiLong
 * @date 8/6/2021
 * @desc 往字符串中插入最少的字符使其成为回文字符串
 */
public class MinCostToPalindrome {
    public static void main(String[] args) {
         String str="fuck";
        System.out.println("min_insertions(str) = " + minInsertions(str));
    }
    static int minInsertions(String s) {
        int[][] memo = new int[s.length()][s.length()];

        for (int i = 0; i < s.length(); ++i) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }

        return helper(s.toCharArray(), 0, s.length() - 1, memo);
    }

    static int helper(char[] sArr, int left, int right, int[][] memo) {
        if (left >= right) {
            return 0;
        }

        if (memo[left][right] != Integer.MAX_VALUE) {
            return memo[left][right];
        }

        if (sArr[left] == sArr[right]) {
            return memo[left][right] = helper(sArr, left + 1, right - 1, memo);
        }

        return memo[left][right] = Math.min(helper(sArr, left + 1, right, memo),
                helper(sArr, left, right - 1, memo)) + 1;

    }

}
