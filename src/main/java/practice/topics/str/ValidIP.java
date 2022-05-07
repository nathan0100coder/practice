package practice.topics.str;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shiLong
 * @date 8/14/2021
 * @desc 合法的IP字符串
 */
public class ValidIP {
    private static final String NO_SUCH_BUCKET_MESSAGE = "Bucket does not exist";
    static String IPv6char = "0123456789abcdefABCDEF";
    public static void main(String[] args){
        String ip="2001:0db8:85a3:0000:0000:8a2e:0370:7334";
        String ip02="192.168.2.113";
        System.out.println(valid_IP_Address(ip));
    }

    //IPv4的ip地址格式：（1~255）.（0~255）.（0~255）.（0~255）
    static boolean isIpLegal(String ipStr) {
        String ipRegEx = "^([1-9]|([1-9][0-9])|(1[0-9][0-9])|(2[0-4][0-9])|(25[0-5]))(\\.([0-9]|([1-9][0-9])|(1[0-9][0-9])|(2[0-4][0-9])|(25[0-5]))){3}$";
        Pattern pattern = Pattern.compile(ipRegEx);
        Matcher matcher = pattern.matcher(ipStr);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    static String valid_IP_Address(String IP) {
        System.out.println(NO_SUCH_BUCKET_MESSAGE);
        String[] IPv4 = IP.split("\\.");
        boolean is_IPv4 = true;
        if(IPv4.length == 4 && NumberOfToken(IP,'.') == 3) {
            for(int i = 0; i < 4; i++) {
                if(!valid_IPv4Number(IPv4[i])){
                    is_IPv4 = false;
                    break;
                }
            }
        }
        else is_IPv4 = false ;
        if(is_IPv4)return "IPv4";

        String[] IPv6 = IP.split(":");
        boolean is_IPv6 = true;
        if(IPv6.length == 8 && NumberOfToken(IP,':') == 7) {
            for(int i = 0; i < 8; i++){
                if(!valid_IPv6Number(IPv6[i])){
                    is_IPv6 = false;
                    break;
                }

            }
        }
        else is_IPv6 = false;
        if(is_IPv6)return "IPv6";

        return "Neither";
    }

    static boolean valid_IPv4Number(String str){
        int num = 0;
        if(str.equals("") || str.length() > 3) {
            return false;
        }
        if(str.length() > 1 && str.charAt(0) == '0' ) {
            return false;
        }
        for (int i = 0; i < str.length(); ++i){
            if ( !Character.isDigit(str.charAt(i))) {
                return false;
            }
            else {
                num = num * 10 + str.charAt(i)-'0';
            }
        }
        if(num >= 0 && num < 256) {
            return true;
        }
        else return false;
    }

    static boolean valid_IPv6Number(String str) {
        if(str.equals("") || str.length() > 4) {
            return false;
        }
        for (int i = 0; i < str.length(); ++i) {
            if ( IPv6char.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }
    static int NumberOfToken(String IP,char token){
        int num = 0;
        for (int i = 0; i< IP.length(); ++i){
            if( IP.charAt(i) == token) {
                num ++;
            }
        }
        return num;
    }
}
