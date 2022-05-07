package practice.applications.utils;

/**
 * @Author: nathan
 * @Date: 2021/4/14
 * @Description: 判断是否为纯中文
 * @Version: 1.0
 */
public class StrRegex {
    private static final String regex = "[\\u4e00-\\u9fa5]+";
    private static final String MAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    public static void main(String[] args) {
        String mail = "17721516285@163.com";
        System.out.println(isMail(mail));
    }

    /**
     * 是否纯中文
     *
     * @param str
     * @return
     */
    public static Boolean isChinese(String str) {
        if (str.matches(regex)){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 是否邮箱
     *
     * @param str
     * @return
     */
    public static Boolean isMail(String str) {
        if (str.matches(MAIL)){
            return true;
        }
        else {
            return false;
        }
    }
}