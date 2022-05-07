package practice.methods.backTrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author shiLong
 * @date 7/25/2021
 * @desc
 */
public class BracketsGenerating {
    public static void main(String[] args) {
        System.out.println("PLEASE INPUT YOUR NUMBER: ");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        List<String> res = generateParenthesis(num);
        res.forEach(System.out::println);
        System.out.println("共用 "+res.size()+" 组解.");

    }
    private static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(n, n, "", res);
        return res;
    }

    // DFS
    private static void helper(int nL, int nR, String parenthesis, List<String> res) {
        // nL 和 nR 分别代表左右括号剩余的数量
        if (nL < 0 || nR < 0) {
            return;
        }

        if (nL == 0 && nR == 0) {
            res.add(parenthesis);
            return;
        }
        helper(nL - 1, nR, parenthesis + "(", res);
        if (nL >= nR) {
            return;
        }
        helper(nL, nR - 1, parenthesis + ")", res);
    }

}
