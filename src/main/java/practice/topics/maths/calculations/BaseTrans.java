package practice.topics.maths.calculations;

import java.util.Stack;

/**
 * @author shiLong
 * @date 7/18/2021
 * @desc 进制转换
 */
public class BaseTrans {
    private static char[] array = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .toCharArray();
    private static String numStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        System.out.println(_10_to_N(273, 16));
        System.out.println("======================================");
        System.out.println(N_to_10("3e8",16 ));

    }
    //    1给定一个字符串类型表示的小数，输出其二进制表示
    /**
     * 将十进制小数转化为二进制
     */
    public static String to_binary(double x,int accurate){
        //精确位数
        int[] binX = new int[accurate];
        StringBuilder binXSB= new StringBuilder();
        double x1 = x;
        double x2=0;
        for(int i=0;i<binX.length;i++){
            x2 = x1+x1;
            x1 =Math.floor(x2);
            binX[i]=(int)x1;
            x1=x2-x1;
            binXSB.append(Integer.toString(binX[i]));
        }
        return binXSB.toString().toString();
    }
    //10进制转为其他进制，除留取余，逆序排列
    public static String _10_to_N(long number, int N) {
        Long rest = number;
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            stack.add(array[new Long((rest % N)).intValue()]);
            rest = rest / N;
        }
        for (; !stack.isEmpty();) {
            result.append(stack.pop());
        }
        return result.length() == 0 ? "0":result.toString();
    }

    // 其他进制转为10进制，按权展开
    public static long N_to_10(String number, int N) {
        char ch[] = number.toCharArray();
        int len = ch.length;
        long result = 0;
        if (N == 10) {
            return Long.parseLong(number);
        }
        long base = 1;
        for (int i = len - 1; i >= 0; i--) {
            int index = numStr.indexOf(ch[i]);
            result += index * base;
            base *= N;
        }
        return result;
    }
}
