package practice.topics.arr.sort;

/**
 * @author shiLong
 * @date 2021/8/22
 * @desc 鸡尾酒排序
 */
public class CocktailsSort {
    public static void main(String[] args) throws InterruptedException {
        int[] array = {9, 7, 3, -1, 5, 2, 6, 8, 10, 3};
        sort(array);
        for (int var : array) {
            System.out.println(var);
        }
    }

    static void sort(int[] array) {
        int tmp = 0;
        for (int i = 0; i < array.length / 2; i++) {
            boolean isSorted = true;
            for (int j = i; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
            //偶数轮之前，重新标记为true
            isSorted = true;
            //偶数轮，从右向左比较和交换
            for (int j = array.length - i - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {

                    tmp = array[j];

                    array[j] = array[j - 1];

                    array[j - 1] = tmp;

                    isSorted = false;
                }

            }
            if (isSorted) {
                break;
            }
        }

    }
}
