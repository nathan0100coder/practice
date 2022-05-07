package practice.tmp;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author shiLong
 * @version 1.0
 * @desc 字符串练习
 * @date 2022/4/28
 */
public class StringPrac {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Asy","THE","Beyond");
        Collections.sort(list);
        list.forEach(System.out::println);
    }


































    private static String pathReverse(String departmentPath) {
        if (StringUtils.isEmpty(departmentPath)){
            return departmentPath;
        }
        return reverse(departmentPath);
    }
    private static String reverse(String str){
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(str) || str.length() < 2){
            return str;
        }
        String[] pathArr = str.split("/");
        if (pathArr.length < 2){
            return str;
        }
        int num = pathArr.length / 2;
        for (int i = 0; i < num; i++) {
            //交换
            swap(pathArr,i,pathArr.length - 1 - i);
        }

        for (int i = 0; i < pathArr.length; i++) {
            if (i > 0){
                sb.append("/").append(pathArr[i]);
            }else {
                sb.append(pathArr[i]);
            }
        }
        return sb.toString();
    }


    private static void swap(String[] arr, int i, int j) {
        String tmp;
        tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }



}
