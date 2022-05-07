package practice.topics.arr.sort;


import java.util.Arrays;


/**
 *
 * 堆是一棵顺序存储的完全二叉树
 *
 *
 *
 *
 */
public class HeapSort {
	public static void main(String[] args) {
		int[] array = {7,6,5,-41,3,72,10,-98,45};
		heap_sort(array);
		System.out.println("Arrays.toString(array) = " + Arrays.toString(array));

	}


	/**
	 * 堆排序    --逻辑:对于数组循环构建大顶堆 ---循环n - 1次  ----,每一次构建后,交换数组的第一个与最后一个元素
	 *
	 *
	 *
	 * @param arr 待排序数组
	 */
	public static void heap_sort(int[] arr){
		for (int j = 0;j < arr.length - 1;j++){
			max_heap_build(arr,arr.length - 1 - j);
			arr[0] ^= arr[arr.length - 1 - j];
			arr[arr.length -1 -j] ^= arr[0];
			arr[0] ^= arr[arr.length -1 -j];
		}
	}







	/**
	 *
	 * 构建大根堆
	 *
	 *
	 *
	 *
	 * @param arr 数组
	 * @param end end索引
	 */
	static void max_heap_build(int[] arr,int end){
		int n = end + 1;

		for (int i = n / 2 - 1;i >= 0;i--){
			int left = 2 * i + 1;
			int right = left + 1;
			int max = left;

			if (right <= n-1 && arr[left] < arr[right]){
				max = right;
			}

			if (arr[i] < arr[max]){
				arr[max] ^= arr[i];
				arr[i] ^= arr[max];
				arr[max] ^= arr[i];

			}
		}
	}









}