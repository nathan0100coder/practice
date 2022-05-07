package practice.methods.divideAndConque;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author shiLong
 * @date 7/25/2021
 * @desc 分治算法之对输入的运算表达式添加括号,输出所有的可能结果值
 */
public class DiffWaysToCompute {
    public static void main(String[] args) {
        System.out.println("PLEASE INPUT THE EXPRESSION: ");
        String next = new Scanner(System.in).next();
        System.out.println(diffWaysToCompute(next));
    }
    private static List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // 扫描算式 input 中的运算符
            if (c == '-' || c == '*' || c == '+') {
                /****** 分 ******/
                // 以运算符为中心，分割成两个字符串，分别递归计算
                List<Integer>
                        left = diffWaysToCompute(input.substring(0, i));
                List<Integer>
                        right = diffWaysToCompute(input.substring(i + 1));
                /****** 治 ******/
                // 通过子问题的结果，合成原问题的结果
                for (int a : left)
                    for (int b : right)
                        if (c == '+')
                            res.add(a + b);
                        else if (c == '-')
                            res.add(a - b);
                        else if (c == '*')
                            res.add(a * b);
            }
        }
        // base case
        // 如果 res 为空，说明算式是一个数字，没有运算符
        if (res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }
}
