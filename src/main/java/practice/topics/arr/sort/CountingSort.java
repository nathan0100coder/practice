package practice.topics.arr.sort;

import java.util.Arrays;

/**
 * @author shiLong
 * @date 7/5/2021
 * @desc
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] array = {5, 1, 1, 1, 9,9,9,9,7,7,7,7, 5, 7, 7, 7, 9, 1, 9, 5, 3, 7, 6, 1};
        countingSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

    // 计数排序,a 是数组,n 是数组大小。假设数组中存储的都是非负整数。
    static void countingSort(int[] arr, int n) {
        if (n <= 1) {
            return;
        }

        // 查找数组中数据的范围
        int max = arr[0];
        for (int i = 1; i < n; ++i) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        // 申请一个计数数组 c,下标大小 [0,max]
        int[] c = new int[max + 1];
        for (int i = 0; i <= max; ++i) {
            c[i] = 0;
        }

        // 计算每个元素的个数,放入 c 中
        for (int i = 0; i < n; ++i) {
            c[arr[i]]++;
        }

        // 依次累加
        for (int i = 1; i <= max; ++i) {
            c[i] = c[i - 1] + c[i];
        }

        // 临时数组 r,存储排序之后的结果
        int[] tmp = new int[n];
        // 计算排序的关键步骤,有点难理解
        for (int i = n - 1; i >= 0; --i) {
            //放入tmp哪里
            int index = c[arr[i]] - 1;

            tmp[index] = arr[i];
        //放过了元素   相应的c[]值减一
            c[arr[i]]--;
        }
        // 将结果拷贝给 arr 数组
//        for (int i = 0; i < n; ++i) {
//            arr[i] = r[i];
//        }
        System.arraycopy(tmp, 0, arr, 0, n);
    }

}
