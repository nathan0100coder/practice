package practice.topics.arr;

import java.util.Arrays;

/**
 * @author shiLong
 * @date 8/6/2021
 * @desc 荷兰国旗问题
 */
public class DutchFlag {
    public static void main(String[] args) {
        int[] arr={2,0,2,2,2,2,0,0,1,1,0};
        sort_Colors(arr);
        System.out.println("arr = " + Arrays.toString(arr));

    }
    static void sort_Colors(int[] A) {
        int n=A.length;
        if(n==0)
            return;

        //三路快排思路   交换原理

        int p_0=0; //0元素部分从0位置开始
        int p_2=n-1;// 2元素部分从最后一个位置往前放
        int i=0;
        while(i<p_2+1)
        {
            if(A[i]==0)  //属于第一部分
            {
                int tmp=A[p_0];
                A[p_0]=A[i];
                A[i]=tmp;
                p_0++;          // 将0元素踢到前面   两者交换即可
                i++;
            }
            else if(A[i]==2)
            {
                int tmp=A[p_2];
                A[p_2]=A[i];
                A[i]=tmp;
                p_2--;          // 将2元素踢到后面   两者交换即可
            }
            else  //A[i]=1   不做任何处理 跳过
            {
                i++;
            }

        }

    }
}
