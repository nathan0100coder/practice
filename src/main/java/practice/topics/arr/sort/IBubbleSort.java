package practice.topics.arr.sort;

/**
 * @Author shiLong
 * @Desc 冒泡排序
 * @Date 2021/10/7 0:20
 * @Version 1.0
 */
public interface IBubbleSort {
    /**
     * 冒泡排序
     *
     * @param list 待排序数组
     * @param <T>  泛型
     * @return     排序后数组
     */
    <T extends Comparable<T>> T[] sort(T[] list);

}
