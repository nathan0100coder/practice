package practice.topics.arr;

public class InversePairs {
    public static void main(String[] args) {
        int[] arr={5,-3,7,9,3,2};//3
        System.out.println("how_many_pairs(arr) = " + how_many_pairs(arr));
    }
    static int how_many_pairs(int[] array) {
        if (array == null || array.length == 0) return 0;
        return inversePairs(array, 0, array.length - 1) % 1000000007;
    }

    static int inversePairs(int[] a, int low, int high) {
        int count = 0;
        if (low < high) {
            int mid = (low + high) / 2;
            count += inversePairs(a, low, mid) % 1000000007;
            count += inversePairs(a, mid + 1, high) % 1000000007;
            count += merge(a, low, mid, high) % 1000000007;
        }
        return count % 1000000007;
    }

    static int merge(int[] a, int low, int mid, int high) {
        int count = 0;
        int i = low, j = mid + 1, k = 0;//i指向第一个有序区间的第一个元素，j指向第二有序区间的第一个元素
        int[] temp = new int[high - low + 1];//临时数组，暂存合并的有序列表
        while (i <= mid && j <= high) {//顺序比较两个区域的元素，将最小的存入临时数组中
            if (a[i] <= a[j]) temp[k++] = a[i++];
            else {
                temp[k++] = a[j++];
                count += mid + 1 - i;
                count = count % 1000000007;
            }
        }

        while (i <= mid) {//第一个区域元素有剩余
            temp[k++] = a[i++];
        }

        while (j <= high) {//第二个区域元素有剩余
            temp[k++] = a[j++];
        }

        for (i = low, k = 0; i <= high; i++, k++) {    //将排好序的元素，重新存回到A中
            a[i] = temp[k];
        }

        return count % 1000000007;
    }
}  