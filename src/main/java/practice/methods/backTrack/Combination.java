package practice.methods.backTrack;

import java.util.LinkedList;
import java.util.List;

class Combination {
    public static void main(String[] args) {
        int n=5,k=3;
        combine(n,k).forEach(System.out::println);
    }

    /**
     *
     * @param n n选k组合
     * @param k n选k组合
     * @return  n选k组合
     */
    static List<List<Integer>> combine(int n,int k) {
        List<List<Integer>> results = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        
        dfs(nums, track, results, k, 0);
        results.forEach(System.out :: println);
        return results;
    }
    
    static void dfs(int[] nums, LinkedList<Integer> track, List<List<Integer>> results, int k, int start) {
        if (track.size() == k){
            results.add(new LinkedList<>(track));
            return;
        }

        for(int i = start; i < nums.length; i++){
            track.add(nums[i]);
            dfs(nums, track, results, k, i + 1);
            track.removeLast();
        }
    }
}
