package practice.topics.arr.sort;

import java.util.Arrays;

public class ShellSort {




    /**
     * 简单插入排序很循规蹈矩，不管数组分布是怎么样的，依然一步一步的对元素进行比较
     * ，移动，插入，比如[5,4,3,2,1,0]这种倒序序列，数组末端的0要回到首位置很是费劲，
     * 比较和移动元素均需n-1次。而希尔排序在数组中采用跳跃式分组的策略，通过某个增量将数组元素划分为若干组，
     * 然后分组进行插入排序，随后逐步缩小增量，继续按组进行插入排序操作，直至增量为1。
     * 希尔排序通过这种策略使得整个数组在初始阶段达到从宏观上看基本有序，
     * 小的基本在前，大的基本在后。然后缩小增量，到增量为1时，其实多数情况下只需微调即可，不会涉及过多的数据移动。
     *
     *
     *
     *
     *
     * @param array   待排序数组
     */
    public static void shellSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int length = array.length;
        int temp;
        int gap = length / 2;
        while (gap > 0) {
            for (int i = gap; i < length; i++) {
                temp = array[i];
                int pre = i - gap;
                while (pre >= 0 && array[pre] > temp) {
                    array[pre + gap] = array[pre];
                    pre -= gap;
                }
                array[pre + gap] = temp;
            }
            gap /= 2;
        }
    }




    public static void main(String[] args) {
        int[] array = {9,7,8,10,1,4,3,2,5,6,11};
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }
}