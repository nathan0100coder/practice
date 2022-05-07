package practice.topics.str;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author shiLong
 * @date 8/6/2021
 * @desc 字符串压缩
 */
public class StringCompress {
    public static void main(String[] args) {
        String str="fuckthatbitch";//f 1 u 1 c 2 k1 t 3 h 2 a 1 b1 i1
        System.out.println("real_compress(str) = " + real_compress(str));
    }
    static String real_compress(String s) {
        AtomicReference<String> res= new AtomicReference<>("");
        List<Character> list = new ArrayList<>();
        int[] counter = new int[127];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (counter[c] == 0)
            {
                list.add(c);
            }
            counter[c]++;               //记录重复元素的个数

        }
        Collections.sort(list);
        list.forEach(e->{
            res.updateAndGet(v -> v + e + counter[e]);
        });
        return res.get();
    }
    static int compress_info(char[] chars) {

        int[] counter = new int[127];   //题目限制chars数组中的字符ASCII的上限为126
        int size = 0;                   //记录字符的种类
        for (char c : chars) {
            if (counter[c] == 0)
                size++;
            counter[c]++;               //记录重复元素的个数
        }
        return size == 1 ? 1 : size * 2;//如果只有一个字符不必压缩
    }

    static int compress(char[] chars) {
        int pTop = 0;
        int N = chars.length;
        int counter = 1;

        for (int i = 0; i < N - 1; i++) {
            if (chars[i] == chars[i+1]) {
                counter++;                 //记录重复字符
            }else {
                chars[pTop++] = chars[i];  //不同字符压栈
                if (counter > 1) {
                    for (char c : (counter + "").toCharArray())
                        chars[pTop++] = c; //添加数量大于1的字符个数
                }
                counter = 1;
            }
        }
        //处理最后一类字符
        chars[pTop++] = chars[N - 1];
        if (counter > 1) {
            for (char c : (counter + "").toCharArray())
                chars[pTop++] = c;
        }
        return pTop;
    }
}
