package practice.topics.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shiLong
 * @version 1.0
 * @desc 中英文数字中横线下划线  正则
 * @date 2021/12/27
 */
public class NameRegex {
    public static boolean matches(String word){
        String regex = "^[\\u4e00-\\u9fa5A-Za-z0-9-\\_]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }
}
