package practice.topics.arr;

/**
 * @author shiLong
 * @date 8/6/2021
 * @desc 买股票的最佳时机
 */
public class BestTimeBuying {
    public static void main(String[] args) {
        int[] arr={7,1,5,3,6,4};
        System.out.println("best_time_buy(arr) = " + best_time_buy(arr));
    }
    static int best_time_buy(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }
}
