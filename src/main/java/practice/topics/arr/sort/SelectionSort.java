package practice.topics.arr.sort;

import java.util.Arrays;

/**
 * @author shiLong
 * @date 7/5/2021
 * @desc SelectionSort
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {11, 37, 5, -3, 2, -9, 67, 67, 23, 12, 18};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));

    }


    private static void selectSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        //每次选择一个最大者与索引最后者交换
        //如此操作 n-1次
        for (int i = 0; i < arr.length - 1; i++) {
            int maxIdx = 0;
            //每一次选择最大者循环 n-1-i 次数
            for (int j = 0; j < arr.length - i; j++) {
                //选择出最大者与最后元素交换
                if (arr[j] > arr[maxIdx]) {
                    maxIdx = j;
                }
            }
            //与当前最后一个元素交换  maxIdx len-1-i交换     异或交换会导致0的出现

//            arr[maxIdx] ^= arr[arr.length - 1 - i];
//            arr[arr.length - 1 - i] ^= arr[maxIdx];
//            arr[maxIdx] ^= arr[arr.length - 1 - i];
            swapTwoArrayEle(arr,maxIdx,arr.length - 1 - i);
        }
    }





    private static void swapTwoArrayEle(int[] arr,int i,int j){
        int tmp;
        tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }




    /**
     * 选择排序   --思路:
     *
     * @param arr 待排序数组
     */
    private static void sort(int[] arr) {
        int end = arr.length - 1;
        for (int i = 0; i < end; i++) {  //循环n-1次
            int maxIdx = findMax(arr, end - i);
            //找到最大元素与最后一个元素做交换
            swap(arr, maxIdx, end - i);
        }
    }

    private static int findMax(int[] arr, int end) {          //end:数组最后一个元素索引
        //假设最大值是第一个元素  若有元素更大  则将该元素索引赋值给maxIdx
        int maxIdx = 0;
        for (int i = 1; i <= end; i++) {
            if (arr[i] > arr[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }


    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];

    }


    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx])
                    minIdx = j;
            }
            int tmp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }


    static int[] selectionSortByIdx(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int maxIdx = array.length - 1 - i;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[maxIdx]) maxIdx = j;
            }
            //maxIdx
            int tmp = array[array.length - 1 - i];
            array[array.length - 1 - i] = array[maxIdx];
            array[maxIdx] = tmp;
        }
        return array;
    }

}
