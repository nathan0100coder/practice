package practice.methods.backTrack;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author shiLong
 * @date 8/14/2021
 * @desc sth
 */
public class Arrangement {
    public static void main(String[] args) {
        int[] arr={2,3,5,7};
        List<List<Integer>> arrange = arrange(arr);
        arrange.forEach(System.out::println);
    }
    /**
     * 不重复数组的全排列
     *
     * @param nums   待排列数组
     * @return       排列结果集
     */
    static List<List<Integer>> arrange(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if(nums == null) {
            return ret;
        }
        dfs(nums, new ArrayList<>(),ret);
        return ret;
    }
    /**
     * 深度优先搜索                                                              ----从数组中找到不同的全排列 放入ret集合 利用list作为临时容器
     *
     *
     * @param nums    原数组
     * @param tmp     临时收集排列的容器
     * @param result  结果集
     */
    static void dfs(int[] nums, List<Integer> tmp,List<List<Integer>> result){
        int length = nums.length;
        //递归退出条件
        if(tmp.size() == length){
            result.add(new ArrayList<>(tmp));
            return;
        }
        for (int num : nums) {
            if (tmp.contains(num)) {
                continue;
            }
            tmp.add(num);
            //递归
            dfs(nums, tmp, result);
            //回溯  remove最后一个元素? why?
            tmp.remove(tmp.size() - 1);
        }

    }










    static Set<List<Integer>> ret_2 = new HashSet<>();


































    //可能重复
    static List<List<Integer>> permute_dup(int[] nums) {
        if(nums == null) return new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, new ArrayList<Integer>(), visited);
        return new ArrayList<>(ret_2);
    }
    static void dfs(int[] nums, ArrayList<Integer> list, boolean[] visited){
        if(list.size() == nums.length){
            ret_2.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(visited[i]) continue;
            list.add(nums[i]);
            visited[i] = true;
            dfs(nums, list, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
    /**
     * 字符串字符全排列
     *
     * @param s 字符数组
     * @param from from
     * @param to to
     */
    public static void string_permutation(char[] s,int from,int to) {
        if(to <= 1)
            return;
        if(from == to) {
            System.out.println(s);
        } else {
            for(int i=from; i<=to; i++) {
                swap(s,i,from); //交换前缀，使其产生下一个前缀
                string_permutation(s, from+1, to);
                swap(s,from,i); //将前缀换回，继续做上一个前缀的排列
            }
        }
    }

    public static void swap(char[] s,int i,int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }


}
