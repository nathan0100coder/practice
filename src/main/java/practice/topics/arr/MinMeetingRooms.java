package practice.topics.arr;

import java.util.Arrays;

/**
 * @author shiLong
 * @date 8/6/2021
 * @desc 安排最少的会议室
 */
public class MinMeetingRooms {
    public static void main(String[] args) {
        int[][] matrix = {
                {8, 9},
                {10, 11},
                {8, 10}
        };
        System.out.println("arrange(matrix) = " + arrange(matrix));
    }

    static int arrange(int[][] meetings) {
        int n = meetings.length;
        int[] begin = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            begin[i] = meetings[i][0];
            end[i] = meetings[i][1];
        }
        Arrays.sort(begin);
        Arrays.sort(end);

        // 扫描过程中的计数器
        int count = 0;
        // 双指针技巧
        int res = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (begin[i] < end[j]) {
                // 扫描到一个红点
                count++;
                i++;
            } else {
                // 扫描到一个绿点
                count--;
                j++;
            }
            // 记录扫描过程中的最大值
            res = Math.max(res, count);
        }

        return res;
    }
}
