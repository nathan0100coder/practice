package practice.topics.maths.cryptography;

/**
 * @author shiLong
 * @date 8/12/2021
 * @desc 密码学问题
 */
public class Cryptography {
    static char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {
        String txt = "FUCKTHATBITCH";
        System.out.println(encode(txt));
    }

    //a b c d e ...--->指向当前索引加7
    static char encode_char(char ch) {
        char ans = 0;
        if (ch >= 84 && ch <= 90) {
            ans = (char) ((ch + 7) - 26);
        } else {
            ans = (char) ((ch + 7));
        }
        return ans;
    }
    static String encode(String txt) {
        String ans = "";//todo
        char[] chars = txt.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = encode_char(chars[i]);
        }
        for (char var:chars){
            ans+=var;
        }
        return ans;
    }

}
