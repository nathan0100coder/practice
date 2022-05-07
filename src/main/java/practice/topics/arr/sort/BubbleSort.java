package practice.topics.arr.sort;

import java.util.Arrays;

/**
 * @author shiLong
 *
 *
 * @date 7/5/2021
 * @desc bubbleSort
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr={11,17,7,5,4,3,2};
        bubbleSort(arr);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

    }





    private static void bubbleSort(int[] arr){
        if (arr == null || arr.length <= 1){
            return;
        }
        //冒n-1个泡
        for (int i = 0; i < arr.length - 1 ; i++) {
            //每一个冒泡循环n-1-i次
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //比较交换
                if (arr[j] > arr[j + 1]){
                    arr[j] ^= arr[j + 1];
                    arr[j + 1] ^= arr[j];
                    arr[j] ^= arr[j + 1];
                }
            }
        }
    }















































    /**
     *冒泡排序               ------冒泡排序  思路:  大则右移;每趟循环将左边一个元素移动到右侧  每个元素的移动需要若干次比较
     *                                            外层循环:所有的元素右移  内层循环:每一个元素的比较移动
     *                                            外层循环:循环次数 n-1   内层循环:每一个元素比较次数 n-i
     *
     *
     * @param arr    arr
     */
    private static void bubbleSort(Integer[] arr){
        int length = arr.length;
        for (int i=0;i < length-1; i++){
            for (int j = 0; j < length-i-1; j++) {
                if (arr[j] > arr[j+1]){
                    //交换位置
                    arr[j]^=arr[j+1];
                    arr[j+1]^=arr[j];
                    arr[j]^=arr[j+1];
                }
            }
        }
    }































    private static Integer[] sort2(Integer[] arr){
        //多少趟 n-1趟
        //每一趟比较次数 1--n-1 2--n-2  k--n-k
        int length = arr.length;
        for (int i=0;i<length-1;i++){
            //每一趟
            for (int j=0;j<length-(i+1);j++){
                //比较 交换
                if (arr[j]>arr[j+1]){
                    //交换两者
                    arr[j]^=arr[j+1];
                    arr[j+1]^=arr[j];
                    arr[j]^=arr[j+1];
                }
            }
        }
        return arr;
    }


    private static Integer[] sort(Integer[] arr) {
        int len = arr.length;
        //out loop:n-1 in loop:n-1-i done.
        for (int i=0;i<len-1;i++){
            for (int j=0;j<len-1-i;j++){
                if (arr[j] > arr[j+1]) {
                    arr[j]=arr[j]^arr[j+1];
                    arr[j+1]=arr[j]^arr[j+1];
                    arr[j]=arr[j]^arr[j+1];
                }
            }
        }
        return arr;
    }
}
