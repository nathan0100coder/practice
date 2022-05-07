package practice.topics.str;

/**
 * @author shiLong
 * @date 8/6/2021
 * @desc 最长公共子串
 */
public class LongestCommonSubstr {
    public static void main(String[] args) {
//        Thread
        String s1="fuck";
        String s2="funny";
        longest_common_substr_2(s1.toCharArray(),s2.toCharArray());
    }

    static void longest_common_substr_2(char[] str1, char[] str2)
    {
        int len=0,max=0;
        int row=0;
        int col=str2.length-1;
        //计算矩阵中的每一条斜对角线上值。
        while(row<str1.length)
        {
            int i=row;
            int j=col;
            while(i<str1.length&&j<str2.length)
            {
                if(str1[i]==str2[j])
                {
                    len++;
                    max=Math.max(max, len);
                }
                else {
                    len=0;
                }
                i++;
                j++;
            }
            if(col>0)
            {
                col--;
            }
            else {
                row++;
            }
        }

        System.out.println(max);
//        return max;
    }
    public static void longest_common_substr(char str1[],char str2[])
    {
        int dp[][]=new int[str1.length][str2.length];
        //对dp矩阵的第一列赋值
        for(int i=0;i<str1.length;i++)
        {
            if(str2[0]==str1[i])
                dp[i][0]=1;
            else {
                dp[i][0]=0;
            }
        }
        //对dp矩阵的第一行赋值
        for(int j=0;j<str2.length;j++)
        {
            if(str1[0]==str2[j])
                dp[0][j]=1;
            else {
                dp[0][j]=0;
            }
        }
        for(int i=1;i<str1.length;i++)
            for(int j=1;j<str2.length;j++)
            {
                if(str1[i]==str2[j])
                {
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else {
                    dp[i][j]=0;
                }
            }
        int max=dp[0][0];
        for(int i=0;i<str1.length;i++) {
            for(int j=0;j<str2.length;j++)
            {
                max=Math.max(max,dp[i][j]);
            }
        }
        System.out.println(max);
    }
}
