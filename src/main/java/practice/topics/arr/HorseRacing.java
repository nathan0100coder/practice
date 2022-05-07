package practice.topics.arr;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author shiLong
 * @date 8/6/2021
 * @desc 田忌赛马
 */
public class HorseRacing {
    public static void main(String[] args) {
       //us = [12,24,8,32]
        //they = [13,25,32,11]
        int[] us={12,24,8,32};//24,32,8,12]
        int[] they={13,25,32,11};
        int[] strategy = horse_racing(us, they);
        System.out.println("strategy = " + Arrays.toString(strategy));
    }
    static int[] horse_racing(int[] us, int[] they) {
        int n = us.length;
        // 给 they 降序排序
        PriorityQueue<int[]> maxpq = new PriorityQueue<>(
                (int[] pair1, int[] pair2) -> {
                    return pair2[1] - pair1[1];
                }
        );
        for (int i = 0; i < n; i++) {
            maxpq.offer(new int[]{i, they[i]});
        }
        // 给 us 升序排序
        Arrays.sort(us);

        // us[left] 是最小值，us[right] 是最大值
        int left = 0, right = n - 1;
        int[] res = new int[n];

        while (!maxpq.isEmpty()) {
            int[] pair = maxpq.poll();
            // maxval 是 they 中的最大值，i 是对应索引
            int i = pair[0], maxval = pair[1];
            if (maxval < us[right]) {
                // 如果 us[right] 能胜过 maxval，那就自己上
                res[i] = us[right];
                right--;
            } else {
                // 否则用最小值混一下，养精蓄锐
                res[i] = us[left];
                left++;
            }
        }
        return res;
    }
}
