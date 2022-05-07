package practice.topics.arr.sort;


import cn.hutool.core.util.PrimitiveArrayUtil;

import java.util.Arrays;

/**
 * @author shiLong
 * @date 7/5/2021
 * @desc insertSort
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {89,5, 6, 7, 3, -1,-13, 23, 2, 8,-78};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     *
     *
     *
     * 插入排序      ---2-n ele插入排序    每一次插入,
     *    1   1   2   2   ~    n-1  n-1
     *
     *
     *
     *
     *
     * @param arr   待排数组
     */
    private static void insertionSort(int[] arr){
        int length = arr.length;
        //2-n ele插入数组
        for (int i = 1; i < length; i++) {
            //每一个元素插入操作
            //执行 i 次
            for (int j = 0; j < i; j++) {
                //执行插入
                //比较交换
                if (arr[i - j] < arr[i - j - 1]){
                    PrimitiveArrayUtil.swap(arr, i-j, i - j - 1);
                }
            }
        }



    }





























    static int[] insertSort(int[] array) {
        //循环n-1次  每次循环插入一个数
        for (int i = 1; i < array.length; i++) {
            //to be inserted
            int tmp = array[i];
            int j;
            for (j = i; j > 0 && array[j - 1] > tmp; j--){
                //小数右移
                array[j] = array[j - 1];
            }
            //待插入之数归位
            array[j] = tmp;
        }
        return array;
    }
}
