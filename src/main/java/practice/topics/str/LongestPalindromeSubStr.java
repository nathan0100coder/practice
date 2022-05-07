package practice.topics.str;

/**
 * @author shiLong
 * @date 7/29/2021
 * @desc 最长回文子序列
 */
public class LongestPalindromeSubStr {
    public static void main(String[] args) {
        String string="google";
        System.out.println("longest_Palindrome(string) = " + longest_Palindrome(string));
    }

    /**
     * 中心扩展法
     * @param s 目标字符串
     * @return 最长回文
     */
    static String longest_Palindrome(String s) {
        if (s == null || s.length() < 2) {
            return "";
        }

        int h = 0, t = 0;

        for (int i = 0; i < s.length(); i++) {
            int l1 = expand_helper(s, i, i);       // 以字符作为中心点扩展
            int l2 = expand_helper(s, i, i + 1);   // 以字符间隙作为中心点扩展
            int pl = Math.max(l1, l2);

            if (pl > t - h) {         // 继续循环  有更大的
                h = i - (pl - 1) / 2;
                t = i + pl / 2;
                //i_start = i - (len-1)/2          ------------ length为奇数
                //i_start = i - (len)/2 - 1        ------------ length为偶数
                //i_end = i + (len-1)/2  ==  i + Math.floor(len / 2)      ------------ length为奇数
                //i_end = i + (len)/2    ==  i + Math.floor(len / 2)      ------------ length为偶数
                // -->因此
                //start = i - Math.floor((len - 1) / 2)
                //end = i + Math.floor(len / 2)
            }
        }
        return s.substring(h, t + 1);
    }

    static int expand_helper(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;//r-l-2+1即  r-l-1
    }


    // 马拉车算法
    static String longest_Palindrome_2th(String s) {
        String T = preProcess(s);
        int n = T.length();
        int[] P = new int[n];
        int C = 0, R = 0;
        for (int i = 1; i < n - 1; i++) {
            int i_mirror = 2 * C - i;
            if (R > i) {
                P[i] = Math.min(R - i, P[i_mirror]);// 防止超出 R
            } else {
                P[i] = 0;// 等于 R 的情况
            }

            // 碰到之前讲的三种情况时候，需要利用中心扩展法
            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
                P[i]++;
            }

            // 判断是否需要更新 R
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }

        }

        // 找出 P 的最大值
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }
        int start = (centerIndex - maxLen) / 2; //最开始讲的求原字符串下标
        return s.substring(start, start + maxLen);
    }

    static String preProcess(String s) {
        int n = s.length();
        if (n == 0) {
            return "^$";
        }
        String ret = "^";
        for (int i = 0; i < n; i++)
            ret += "#" + s.charAt(i);
        ret += "#$";
        return ret;
    }
}
