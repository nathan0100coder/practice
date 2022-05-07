package practice.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author shiLong
 * @version 1.0
 * @desc 正则
 * @date 2021/10/21
 */
@Slf4j
public class RegexPrac {
    public static void main(String[] args) {
        String words = " hello the world ";
        System.out.println(reverseWords(words));
    }

    public static String reverseWords(String s)
    {
        String[] wordsArray = s.trim().split("\\s+");
        String s1 = Arrays.toString(wordsArray);
        log.info("--------------s1:{}----------------",s1);
        StringBuilder result = new StringBuilder();
        for(int i = wordsArray.length - 1; i >= 0 ; i--)
        {
            if(i == 0)
            {
                result.append(wordsArray[i]);
            }
            else
            {
                result.append(wordsArray[i] + " ");
            }
        }
        return result.toString();
    }

}
