package practice.topics.arr.sort;

import java.util.Arrays;

/**
 * @author shiLong
 * @date 7/5/2021
 * @desc QuickSort
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {7,9,12,23,9,67,-23,6,8,11};
        int start = 0;
        int end  = arr.length - 1;
        quickSort0(arr, start, end);
        System.out.println("Arrays.toString(ints) = " + Arrays.toString(arr));
    }


    public static int[] quickSort0(int[] array, int start, int end) {
        if(start < 0 || end >= array.length || start > end){
            return null;
        }
        int smallIndex = partition0(array,start,end);
        System.out.println("smallIndex = " + smallIndex);

        if(smallIndex > start){
            quickSort0(array,start,smallIndex-1);
        }
        if(smallIndex < end){
            quickSort0(array,smallIndex + 1,end);
        }
        return array;
    }

    public static int partition0(int[] array,int start,int end){
        //随机取一个基准（pivot)
        int pivot = (int)(start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array,pivot,end);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
        for (int i = start;i <= end;i++){
            if(array[i] <= array[end]){
                smallIndex++;
                if(i > smallIndex){
                    swap(array,i,smallIndex);
                    System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
                }
            }
        }
        return smallIndex;
    }




    /**
     * 快速排序
     *
     *
     * @param array  待排序数组
     * @param left   数组左侧索引
     * @param right  数组右侧索引
     */
    static void quickSort(int[] array,int left,int right) {
        int start = left;
        int end = right;

        int pivot = array[start];

        //从右往左选择小于基准数的元素
        while (pivot <= array[end]) {
            end--;
        }
        //从左往右选择大于基准数的元素
        while (pivot >= array[start]) {
           start++;
        }
        swap2Ele(array,end,start);

        //左递归
        quickSort(array,left,start);
        //右递归
        quickSort(array,end,right);
    }


    static void swap2Ele(int[] arr,int i, int j){
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }





    private static void quickSort01(int[] arr, int left, int right) {
        int low = left;
        int high = right;
        if (low > high) {
            return;
        }
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        quickSort01(arr, left, low - 1);
        quickSort01(arr, low + 1, right);
    }

    /**
     * quickSort02
     *
     * @param arr
     * @param low
     * @param high
     */
    private static void quickSort02(int[] arr, int low, int high) {
        int pivot;
        if (low > high) {
            return;
        }
        int i = low;
        int j = high;
        pivot = arr[low];
        while (i < j) {
            while (pivot <= arr[j] && i < j) {
                j--;
            }
            while (pivot >= arr[i] && i < j) {
                i++;
            }
            if (i < j) {
                int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
            }
        }
        arr[low] = arr[i];
        arr[i] = pivot;
        //recursion left
        quickSort02(arr, low, i - 1);
        //recursion
        quickSort02(arr, i + 1, high);
    }

    /**
     * quickSort03
     *
     * @param arr
     * @param start
     * @param end
     */
    static void quickSort03(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = partition(arr, start, end);

        quickSort03(arr, start, mid - 1);

        quickSort03(arr, mid + 1, end);
    }

    static int partition(int[] arr, int start, int end) {
        int pivot = arr[start];
        while (start < end) {
            while (arr[end] >= pivot && start < end) {
                end--;
            }
            arr[start] = arr[end];
            while (arr[start] < pivot && start < end) {
                start++;
            }
            arr[end] = arr[start];
        }
        arr[start] = pivot;
        return start;
    }


    /**
     * quickSort04
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort04(int[] arr, int left, int right) {
        if (left < right) {
            dealPivot(arr, left, right);
            int pivot = right - 1;
            int i = left;
            int j = right - 1;
            while (true) {
                while (arr[++i] < arr[pivot]) {
                }
                while (j > left && arr[--j] > arr[pivot]) {
                }
                if (i < j) {
                    swap(arr, i, j);
                } else {
                    break;
                }
            }
            if (i < right) {
                swap(arr, i, right - 1);
            }
            quickSort04(arr, left, i - 1);
            quickSort04(arr, i + 1, right);
        }

    }

    public static void dealPivot(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        if (arr[right] < arr[mid]) {
            swap(arr, right, mid);
        }
        swap(arr, right - 1, mid);
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

