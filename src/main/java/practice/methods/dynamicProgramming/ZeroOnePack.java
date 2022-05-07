package practice.methods.dynamicProgramming;

/**
 * @author shiLong
 * @date 7/17/2021
 * @desc 0-1背包问题
 */
public class ZeroOnePack {
    static Integer i,j,maxValue;
    public static void main(String[] args) {
        int n=5,w=11;
        int[] weights={1,2,5,6,7};
        int[] values={1,6,18,22,28};
        System.out.println(zeroOnePack(n, w, weights, values));
        System.out.println("======================================");
        System.out.println(ZeroOnePack_2(w, n, weights, values));
    }

     static int zeroOnePack(int N, int W, int[] weights, int[] values){
        int[] dp = new int[W+1];
        for(i = 1;i <= N; i++){
            int w = weights[i-1], v = values[i-1];
            for(j = W; j >= w; j--){
                dp[j] = Math.max(dp[j], dp[j-w]+v);
            }
        }
        return dp[W];
    }
    /**
     * 动态规划之 0-1背包问题
     * @param V	背包容量
     * @param N	物品种类
     * @param weight 物品重量
     * @param value	物品价值
     * @return  string
     */
     private static String ZeroOnePack_2(int V,int N,int[] weight,int[] value){
        int[][] dp = new int[N+1][V+1];
         for(j=1;j<=V;j++) {
             for(i=1;i<=N;i++) {
                 if(weight[i-1] > j) {
                     dp[i][j] = dp[i-1][j];
                 } else
                     //1 dp[1][0]+6
                 {
                     dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weight[i-1]]+value[i-1]);
                 }
             }
         }
        //则容量为V的背包能够装入物品的最大值为
        maxValue = dp[N][V];
         System.out.println("maxValue = " + maxValue);
         //逆推找出装入背包的所有商品的编号
         j=V;
         StringBuilder numStr = new StringBuilder();
        for(i=N;i>0;i--){
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            if(dp[i][j]>dp[i-1][j]){
                numStr.append(i.toString()).append(" ").append(numStr);
                j=j-weight[i-1];
            }
            if(j==0) {
                break;
            }
        }
        return numStr.toString();
    }


}
