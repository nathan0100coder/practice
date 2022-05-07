package practice.topics.arr.search;

/**
 * @author shiLong
 * @date 7/3/2021
 * @desc
 */
public class BinarySearch {

    private static int binarySearch(int[] arr,int target){
        int lft = 0,rgt = arr.length-1;
        while (lft <= rgt){
            int mid = (lft+rgt)/2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                rgt = mid-1;
            } else {
                lft = mid+1;
            }
        }
        return -1;
    }

}
