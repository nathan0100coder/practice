package practice.topics.arr;

/**
 * @author 诗龙
 * @date 8/16/2021
 * @desc 最小子数组
 */
public class MinSubarr {
    public static void main(String[] args) {
        int[] nums={2,2,4,1,2,5,2,4};
        int s=7;
        System.out.println("min_subarr(nums,s) = " + min_sub_arr(nums, s));
    }
    static int min_sub_arr(int[] nums, int s){
        int left = 0, right = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;

        while(right < nums.length){
            sum += nums[right++];
            while(sum >= s){
                res = Math.min(res, right - left);
                sum -= nums[left++];
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
