package practice.topics.maths;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shiLong
 * @date 8/13/2021
 * @desc 连续的自然数序列 其和等于s
 */
public class Continuous_nums_sum_s {
    public static void main(String[] args){
        int sum=990;
        continuous_nums_sum_s(sum).forEach(System.out::println);
    }
    static List<List<Integer>> continuous_nums_sum_s(int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(sum < 3)
            return res;
        int start = 1, end = 2, mid = (1+sum)/2;
        while(start < mid){
            int s = totalSum(start, end);
            if(s == sum){
                res.add(getSequence(start, end));
                end ++;
            }else if(s < sum){
                end ++;
            }else if(s > sum){
                start ++;
            }
        }
        return res;
    }
    static int totalSum(int start, int end){
        int sum = 0;
        for(int i = start; i <= end; i++){
            sum += i;
        }
        return sum;
    }

    static List<Integer> getSequence(int start, int end){
        List<Integer> temp = new ArrayList<>();
        for(int i = start; i <= end; i++){
            temp.add(i);
        }
        return temp;
    }
}
