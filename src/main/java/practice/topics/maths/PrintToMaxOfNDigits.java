package practice.topics.maths;

import java.util.Arrays;

/**
 * @author shiLong
 * @date 8/12/2021
 * @desc 打印从1到最大的N位数
 */
public class PrintToMaxOfNDigits {
    public static void main(String[] args) {
        int num=5;
        printToMaxOfNDigits1(num);
    }
    public static void printToMaxOfNDigits1(int n) {
        if(n <= 0) {
            return;
        }
        char[] nums = new char[n + 1];
        Arrays.fill(nums, '0');
        while(!increment(nums)) {
            printNum(nums);
        }
    }

    public static boolean increment(char[] nums) {
        int carry = 0;
        for(int i = nums.length - 1; i >= 0; i--) {
            int temp = nums[i] - '0' + carry;
            //因为是加1，所以肯定是在最后一位上加1了
            if(i == nums.length - 1) {
                temp++;
            }
            carry = temp / 10;
            temp %= 10;
            nums[i] = (char)(temp + '0');
        }
        return nums[0] == '1';
    }

    public static void printNum(char[] nums) {
        int index = 0;
        for(; index < nums.length; index++) {
            if(nums[index] != '0'){
                break;
            }
        }
        for(; index < nums.length; index++) {
            System.out.print(nums[index]);
        }
        System.out.println();
    }

}
