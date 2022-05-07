package practice.topics.arr.sort;

import java.util.Arrays;

/**
 * 基数排序
 * 考虑负数的情况还可以参考： https://code.i-harness.com/zh-CN/q/e98fa9
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr={326,453,68,1825,751,435,704,705,704,690,827,129,235};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    // 基数排序
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, max_bits(arr));
    }

    // 基数排序
    public static void radixSort(int[] arr, int begin, int end, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        int[] count = new int[radix];
        int[] bucket = new int[end - begin + 1];
        // 依次遍历每个位数
        for (int d = 1; d <= digit; d++) {

            // 统计数量
            for (i = begin; i <= end; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }

            // 计算位置
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }

            // 记录到对应位置
            for (i = end; i >= begin; i--) {
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];

                count[j]--;
            }
            //copy
            for (i = begin, j = 0; i <= end; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }
    // 计算最大位数
    public static int max_bits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }


    // 获取位数数值
    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }
}