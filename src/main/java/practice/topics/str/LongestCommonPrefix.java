package practice.topics.str;

/**
 * @author shiLong
 * @date 8/14/2021
 * @desc 最长公共前缀
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] arr={"flow","flower","flight"};
        System.out.println(longestCommonPrefix(arr));
    }

    /**
     * 最长公共前缀
     *
     * @param strings 字符串数组
     * @return 最长公共前缀
     */
    static String longestCommonPrefix(String[] strings) {
        if(strings.length==0){
            return "";
        }
        String minLengthString = strings[0];
        int minlength = minLengthString.length();
        for(int i=1; i<strings.length; i++) {
            if(minlength>strings[i].length()){
                minLengthString = strings[i];
                minlength = strings[i].length();
            }
        }
        int index = minlength;
        String result = "";
        while(index>0){
            String subString = minLengthString.substring(0, index);
            boolean isMax = true;
            for(int i=1; i<strings.length; i++){
                if(!strings[i].startsWith(subString)){
                    isMax = false;
                    break;
                }
            }
            if(isMax){
                result = subString;
                break;
            }
            index --;
        }
        return result;
    }
}
