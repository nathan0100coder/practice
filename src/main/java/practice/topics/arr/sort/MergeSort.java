package practice.topics.arr.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {7,9,12,23,9,67,-23,6,8,11};
        int low = 0;
        int high  = arr.length - 1;
        merge_sort(arr, low, high);
        System.out.println("Arrays.toString(ints) = " + Arrays.toString(arr));
    }

    static void merge_sort(int[] arr, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            merge_sort(arr, low, mid);
            merge_sort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }



    static void merge(int[] arr, int low, int mid, int high) {
        int i = low, j = mid + 1, k = 0;
        int[] temp = new int[high - low + 1];
        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= high) {
            temp[k++] = arr[j++];
        }
        if (temp.length >= 0) {
            System.arraycopy(temp, 0, arr, low, temp.length);
        }
    }

}