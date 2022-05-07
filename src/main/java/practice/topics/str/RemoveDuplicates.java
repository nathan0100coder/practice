package practice.topics.str;

import java.util.Stack;

/**
 * @author shiLong
 * @date 8/6/22
 * @desc 删除重复
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        String string = "SEESAAIN";
        System.out.println("removeDuplicates(string) = " + removeDuplicates(string));
    }

    //输入："abbaca"
    //
    //输出："ca"
    static String removeDuplicates(String S) {
        char[] chars = S.toCharArray();
        Stack<Character> stack = new Stack<>();
        int index = 0;
        int length = S.length();
        while (index < length) {
            char current = chars[index++];
            if (!stack.empty() && stack.peek() == current) {
                //如果栈顶的值和当前遍历的值相同，他两直接消失
                stack.pop();
            } else {
                //如果栈为空，或者栈顶元素和当前值不相同，就把当前值压入栈
                stack.push(current);
            }
        }
        //下面是把栈中的元素转化为字符串
        StringBuilder stringBuilder = new StringBuilder(stack.size());
        while (!stack.empty())
            stringBuilder.append(stack.pop());
        return stringBuilder.reverse().toString();
    }
    static String removeDuplicates2(String S) {
        char[] container = new char[S.length()]; // 存储容器
        int p = 0;
        for(char c : S.toCharArray()) {
            if(p == 0 || container[p-1] != c) { //容器空 || 顶部元素不等于c
                container[p++] = c;
            }else {
                p--;
            }
        }
        return p > 0 ? new String(container,0, p) : "";
    }
}
