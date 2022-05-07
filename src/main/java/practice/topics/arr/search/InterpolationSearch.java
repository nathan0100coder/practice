package practice.topics.arr.search;

/**
 * @author shiLong
 * @date 7/3/2021
 * @desc ��ֵ����
 */
public class InterpolationSearch {

    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        int target = 10;
        System.out.println(insert_search(arr,target));//2
    }



    /**
     * len* (target-arr[l])/(arr[r]-arr[l])
     * @param arr
     * @param target
     * @return
     */
    static int insert_search(int[] arr,int target){
        int left=0;
        int right=arr.length-1;
        int mid;
        while (left<=right&&target>=arr[left]&&target<=arr[right]){//��ֹԽ��
            mid=left+(right-left)*(target-arr[left])/(arr[right]-arr[left]);//CORE
            if (target==arr[mid]) return mid;
            if (target>arr[mid])
                left=mid+1;
            else
                right=mid-1;
        }
        return -1;
    }


}
