package practice.topics.str;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shiLong
 * @date 8/6/2021
 * @desc 最小覆盖子串   滑动窗口
 */
public class MinCoveringSubStr {
    public static void main(String[] args) {
        String s1="whatsfuck";
        String s2="kfc";
        System.out.println(min_covering_substr(s1, s2));

    }
    static String min_covering_substr(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < t.length(); i ++) {
            char key = t.charAt(i);
            if(!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
        }
        int count = t.length();
        int left = 0, right = 0;
        int min = Integer.MAX_VALUE, minLeft = 0, minRight = 0;
        while(right < s.length()) {
            char c = s.charAt(right);
            if(map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if(map.get(c) >= 0) {
                    count --;
                }
            }
            //right ++;
            while(count == 0) {
                if(right - left < min) {
                    minLeft = left;
                    minRight = right;
                    min = minRight - minLeft;
                }
                char c2 = s.charAt(left);
                if(map.containsKey(c2)) {
                    map.put(c2, map.get(c2) + 1);
                    if(map.get(c2) > 0) {
                        count ++;
                    }
                }
                left ++;
            }
            right ++;
        }
        return (min == Integer.MAX_VALUE) ? "" : s.substring(minLeft, minRight + 1);
    }

}
