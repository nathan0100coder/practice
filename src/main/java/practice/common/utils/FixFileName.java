package practice.common.utils;

import lombok.extern.slf4j.Slf4j;
import practice.applications.utils.FileSuffix;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shiLong
 * @version 1.0
 * @desc 文件名修正
 * @date 2021/10/21
 */
@Slf4j
public class FixFileName {
    public static void main(String[] args) {
//    String fileName = "W58PI:C58PICdZ*T云想衣裳花想容 yBJ5<>    哈哈哈   98|PI/C//RIWj?8.mp4_10s        .mp4";
//        System.out.println(fixFileName(fileName));



        String words = " hello the world ";
//        String trim = words.trim();
//        System.out.println(trim);
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


    public static String fixFileName(String fileName) {
        String result;
        String tmpContent;
        String tmpSuffix;
        if (fileName.endsWith(FileSuffix.JPEG.getSuffix())) {
            tmpContent = fileName.substring(0, fileName.length() - 5);
            tmpSuffix = FileSuffix.JPEG.getSuffix();
        } else {
            tmpContent = fileName.substring(0, fileName.length() - 4);
            tmpSuffix = fileName.substring(fileName.length() - 4);
        }
        Pattern pattern = Pattern.compile("[\\s\\\\/:\\*\\?\\\"<>\\|\\.\\_]");
        Matcher matcher = pattern.matcher(tmpContent);
        tmpContent = matcher.replaceAll("");
        result = tmpContent + tmpSuffix;
        log.info("-----------------fixedFileName:{}------------------", result);
        return result;
    }
}
