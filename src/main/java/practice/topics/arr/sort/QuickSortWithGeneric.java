package practice.topics.arr.sort;

/**
 * @program: practice
 * @description: 泛型-快排
 * @package_name: practice.topics.arr.sort
 * @author: shiLong
 * @date: 2021-08-29 16:52
 **/
public class QuickSortWithGeneric {
    public static void main(String[] args) {
        String[] arr = {"eve", "celine", "nathan", "ben", "lisa"};
        Double[] doubles = {2.0, -7.983, 90.546, 12.07, 34.0, 15.9};
        Integer[] ints={7,9,12,3,-9,45,6,8,97};
        sort(arr);
        printItem(arr);

    }

    static <T> void printItem(T[] arr) {
        for (T var : arr) {
            System.out.println(var);
        }
    }

    public static <C extends Comparable> void sort(C[] array) {
        if (null == array) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    /**
     * 快排
     *
     * @param array
     * @param start
     * @param end
     * @param <C>
     */
    private static <C extends Comparable> void sort(C[] array, int start, int end) {
        if (end - start < 1) {
            return;
        }
        select(array, start, end);
        C split = array[start];
        int i = start;
        int j = end;
        while (i < j) {
            // 找到比split小的数
            while (i < j && bigger(array[j], split)) {
                j--;
            }
            if (i < j) {
                array[i++] = array[j];
            }
            // 找到比split大的数
            while (i < j && smaller(array[i], split)) {
                i++;
            }
            if (i < j) {
                array[j--] = array[i];
            }
        }
        array[i] = split;
        // 排序左边
        sort(array, start, i - 1);
        // 排序右边
        sort(array, i + 1, end);
    }

    /**
     * 选择策略
     *
     * @param array
     * @param start
     * @param end
     * @param <C>
     */
    private static <C extends Comparable> void select(C[] array, int start, int end) {
        int mid = start + (end - start) / 2;
        if (smaller(array[start], array[mid])) {
            swap(array, start, mid);
        } else if (smaller(array[start], array[end])) {
            swap(array, start, end);
        }
    }

    /**
     * 交换值
     *
     * @param array
     * @param a
     * @param b
     * @param <C>
     */
    private static <C extends Comparable> void swap(C[] array, int a, int b) {
        C t = array[a];
        array[a] = array[b];
        array[b] = t;
    }

    @SuppressWarnings("unchecked")
    public static <C extends Comparable> boolean bigger(C a, C b) {
        return a.compareTo(b) > 0;
    }

    @SuppressWarnings("unchecked")
    public static <C extends Comparable> boolean smaller(C a, C b) {
        return a.compareTo(b) < 0;
    }
}
