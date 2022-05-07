package practice.topics.maths.number_theory.congruence;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 诗龙
 * @date 8/16/2021
 * @desc 中国剩余定理
 */
public class ChineseRemainderTheorem {
    static List<Integer> list;
    public static void main(String[] args){
        int r1=2;
        int m1=3;
        int r2=3;
        int m2=5;
        int r3=2;
        int m3=7;
        chinese_remainder_theorem(2,3,3,5,2,7).forEach(System.out::println);
    }
    //int modulo,int remainder
    static List<Integer> chinese_remainder_theorem(int r1,int m1,int r2,int m2,int r3,int m3){
        list=new ArrayList<>();
        int count=0;
        int X=0;
        while (count<100){
            if (X%3==2&&X%5==3&&X%7==2){
                count++;
                list.add(X);
            }
            X++;
        }
        return list;
    }
}
