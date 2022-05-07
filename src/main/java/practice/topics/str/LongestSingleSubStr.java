package practice.topics.str;

/**
 * @author shiLong
 * @date 8/6/2021
 * @desc 无重复字符的最长的子串
 */
public class LongestSingleSubStr {
    public static void main(String[] args) {
        String s = "justfunnuy";
        System.out.println("lengthOfLongestSubstring(s) = " + lengthOfLongestSubstring(s));//4
    }
    static int lengthOfLongestSubstring(String s){
        int size =s.length();
        int i=0, j, k, max = 0;
        //j 作为外部循环变量，遍历字符串的每一个元素
        //i 初始指向当前检测到的重复元素的下一个元素索引
        //k 用于遍历从 i 到 j 的子串
        for(j = 0; j < size; j++) {
            for(k = i; k < j; k++)
//                if(s[k] == s[j])
                if (s.charAt(k)==s.charAt(j))
                {
                    i = k + 1;
                    break;
                }
            if(j - i + 1 > max)
                max = j - i + 1;
        }
        return max;
    }
}
