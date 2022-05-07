package practice.topics.maths.number_theory.congruence;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shiLong
 * @date 8/14/2021
 * @desc test
 */
public class NthRemaining {
    public static void main(String[] args) {
        int modulo=7;
        int remainder=1;
        int N=10;
        int Exp=3;
        solutions_of_Nth_remaining(modulo,remainder,N,Exp).forEach(System.out::println);
    }

    /**
     * 二次同余方程
     * @param modulo 模
     * @param remainder 剩余
     * @param N 解的个数
     * @return  方程解
     */
    static List<Integer> solutions_of_secondary_remaining(int modulo,int remainder,int N){
        List<Integer> solutions = new ArrayList<>();
        int count=0;
        int X=0;
        while (count<N){
            if ((X * X - remainder) % modulo == 0) {
                count++;
                solutions.add(X);
            }
            X++;
        }
        return solutions;
    }
    static List<Integer> solutions_of_Nth_remaining(int modulo,int remainder,int N,int Exp){
        List<Integer> solutions = new ArrayList<>();
        int count=0;
        int X=0;
        while (count<N){
            if (((int)Math.pow(X,Exp) - remainder) % modulo == 0) {
                count++;
                solutions.add(X);
            }
            X++;
        }
        return solutions;
    }
}
