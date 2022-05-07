package practice.topics.arr;

import java.util.*;

/**
 * @author shiLong
 * @date 8/14/2021
 * @desc N-sum问题
 */
public class N_Sum {
    static int  i,j,k,l;
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        int target1 = 9;
        int[] ints1 = two_sum_(arr, target1);
        String s1 = Arrays.toString(ints1);
        System.out.println("s1 = " + s1);


        int target2 = 100;
        int[] ints2 = two_sum_(arr, target2);
        String s2 = Arrays.toString(ints2);
        System.out.println("s2 = " + s2);
    }

    static int[] two_sum_(int[] arr,int target)
    {
        int[] result = {-1,-1};
        int length = arr.length;
        for (int i = 0; i < length-1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (arr[i] + arr[j] == target){
                    result[0] = arr[i];
                    result[1] = arr[j];
                    return result;
                }
            }
        }
        return result;
    }



    static int[] two_sum_using_map(int[] arr,int target){
        int[] result = {-1,-1};
        int length = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i1 = 0; i1 < length; i1++) {

        }


        return result;
    }




    static int[] two_sum_2(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(target - arr[i])) {
                return new int[]{map.get(target - arr[i]), i};
            }
            map.put(arr[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    static int[] two_sum_3(int[] arr, int target) {
        Arrays.sort(arr);

        return null;
    }
    static List<int[]> three_sum(int[]arr){
        List<int[]> res=new ArrayList<>();
        for (i=0;i<arr.length-2;i++){
            for (j=i+1;j<arr.length-1;j++){
                for (k=j+1;k<arr.length;k++){
                    if (arr[i]+arr[j]+arr[k]==0)
                        res.add(new int[]{i,j,k});
                }
            }
        }
        return res;
    }


    static List<List<Integer>> three_sum_2(int[] num) {
        Arrays.sort(num);//排序
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < num.length-2; ++i){
            if(i > 0 && num[i] == num[i-1]) {
                continue;
            }
            int lo = i+1, hi = num.length-1, sum = -num[i];
            while (lo < hi) {//有序数组找特定和的两元素，双指针算法
                if (num[lo] + num[hi] == sum) {
                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
                    while (lo < hi && num[lo] == num[lo+1]) {
                        lo++;//避免重复三元组
                    }
                    while (lo < hi && num[hi] == num[hi-1]) {
                        hi--;//避免重复三元组
                    }
                    lo++; hi--;
                } else if (num[lo] + num[hi] < sum) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return res;
    }


    //For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
    //
    //    A solution set is:
    //    (-1,  0, 0, 1)
    //    (-2, -1, 1, 2)
    //    (-2,  0, 0, 2)
    static List<List<Integer>> three_sum_good(int[] nums) {
        if (nums.length < 3) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<>();
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        int negSize = 0, posSize = 0;
        int zeroSize = 0;
        for (int v : nums) {
            if (v < minValue) minValue = v;
            if (v > maxValue) maxValue = v;
            if (v > 0) posSize++;
            else if (v < 0) negSize++;
            else zeroSize++;
        }
        if (zeroSize >= 3) res.add(Arrays.asList(0, 0, 0));//输出 三个 0 的情况
        if (negSize == 0 || posSize == 0) return res;
        //此时minValue一定为负数，maxValue一定为正数
        //如果maxValue > -2*minValue，那么大于 -2*minValue的元素肯定不会是答案，可以排除掉，所以更新maxValue
        if (minValue * 2 + maxValue > 0) maxValue = -minValue * 2;
            //同理更新minValue
        else if (maxValue * 2 + minValue < 0) minValue = -maxValue * 2;
        //自己构建一个hashmap，值表示元素重复次数，注意java数组默认值为 0
        int[] map = new int[maxValue - minValue + 1];
        int[] negs = new int[negSize];
        int[] poses = new int[posSize];
        negSize = 0;
        posSize = 0;
        for (int v : nums) {
            if (v >= minValue && v <= maxValue) {//只保留在[minValue,maxValue]区间内的元素
                if (map[v - minValue]++ == 0) {//计数加去重
                    if (v > 0) poses[posSize++] = v;//poses数组存所有去重后的正值
                    else if (v < 0) negs[negSize++] = v;//negs数组存所有去重后的负值
                }
            }
        }
        //正负数两数组排序
        Arrays.sort(poses, 0, posSize);
        Arrays.sort(negs, 0, negSize);
        int basej = 0;
        for (int i = negSize - 1; i >= 0; i--) {//负数数组从后往前遍历
            int nv = negs[i];//nv为当前负数值
            //minp = -nv/2，相当于三元组中另外两元素的平均值，即为另两个元素中较小值的上界，较大值的下界
            int minp = (-nv) >>> 1;
            //定位到正数数组中值刚好小于平均值的元素（这个地方应该还可以优化为使用二分查找）
            while (basej < posSize && poses[basej] < minp) {
                basej++;
            }
            for (int j = basej; j < posSize; j++) {//正数数组从大于等于平均值的元素开始遍历
                int pv = poses[j];//pv 为当前正数值
                int cv = -nv - pv;//cv 为要寻找的另一个值
                //目标值 cv 应该在 [nv,pv] 当中
                //如果不限制cv<=pv，当nv为奇数时，有可能会重复输出
                if (cv >= nv && cv <= pv) {
                    if (cv == nv) {
                        if (map[nv - minValue] > 1)//两个相同的负数和一个正数的情况
                            res.add(Arrays.asList(nv, nv, pv));
                    } else if (cv == pv) {
                        if (map[pv - minValue] > 1)//两个相同的正数和一个负数的情况
                            res.add(Arrays.asList(nv, pv, pv));
                    } else {
                        if (map[cv - minValue] > 0)//三个不同元素的情况
                            res.add(Arrays.asList(nv, cv, pv));
                    }
                } else if (cv < nv) break;//如果 cv 小于 nv了，表明这种情况会在后面寻找，为避免重复输出，跳出循环
            }
        }
        return res;
    }
    static List<List<Integer>> four_sum_(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums==null|| nums.length<4) {
            return result;
        }
        Arrays.sort(nums);
        for(i=0; i<nums.length-3; i++){
            if(i!=0 && nums[i]==nums[i-1]) {
                continue;
            }
            for(j=i+1; j<nums.length-2; j++){
                if(j!=i+1 && nums[j]==nums[j-1]) {
                    continue;
                }
                k=j+1;
                l=nums.length-1;
                while(k<l){
                    if(nums[i]+nums[j]+nums[k]+nums[l]<target){
                        k++;
                    }else if(nums[i]+nums[j]+nums[k]+nums[l]>target){
                        l--;
                    }else{
                        List<Integer> t = new ArrayList<>();
                        t.add(nums[i]);
                        t.add(nums[j]);
                        t.add(nums[k]);
                        t.add(nums[l]);
                        result.add(t);
                        k++;
                        l--;
                        while(k<l &&nums[l]==nums[l+1] ){
                            l--;
                        }
                        while(k<l &&nums[k]==nums[k-1]){
                            k++;
                        }
                    }
                }
            }
        }
        return result;
    }

}
