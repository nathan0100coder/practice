package practice.methods.backTrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *  * 输入一个集合，输出其幂集
 *  * @author nathan
 *  * @time 2021-07-25
 *  *
 * */


public class PowerSet {
    static int loop, index, temp, count;
    static List<Integer> list;
    static String inputString;
    static List<List<Integer>> res;
    static List<Integer> currentCharList;
    public static void main(String[] args) {
        list = new ArrayList<>();
        System.out.println("PLEASE INPUT INTEGERS SEPARATED BY COMMAS：");
        inputString = new Scanner(System.in).next();
        if (inputString != null && !inputString.isEmpty()) {
            String[] strArray = inputString.split(",");
            for (String str : strArray) {
                list.add(Integer.parseInt(str));
            }
            List<List<Integer>> res = getPowerSet(list);
            System.out.println("BELOW IS ITS POWER SET:");
            res.forEach(System.out::println);
        }
    }

    private static List<List<Integer>> getPowerSet(List<Integer> subList) {
        res = new ArrayList<>();
        count = 1 << subList.size();//2^n
        for (loop = 0; loop < count; loop++) {
            index = 0;
            temp = loop;
            currentCharList = new ArrayList<>();
            while (temp > 0) {
                if ((temp & 1) > 0) {//奇数
                    currentCharList.add(subList.get(index));
                }
                temp >>= 1;//除以2
                index++;
            }
            res.add(currentCharList);
        }
        return res;
    }
    static List<List<Integer>> power_set(int[] nums) {
        res = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        backtrack(nums, 0, path);
        return res;
    }

    static void backtrack(int[] nums, int start, LinkedList<Integer> path) {
        //结束条件判断
        res.add(new LinkedList<>(path));
        //通过start来控制选择的起始点
        for(int i = start; i < nums.length; i++) {
            //做出选择
            path.add(nums[i]);
            //递归回溯
            backtrack(nums, i + 1, path);
            //撤销选择
            path.removeLast();
        }
    }
}