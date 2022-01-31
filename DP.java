/**
 * @author LiangHaoFa
 * @version 1.0.0
 * @description //TODO
 * @date 2022/1/29 15:22
 */
public class DP {



    public int maxValue(int[][] grid) {
        if (grid.length == 0){
            return 0;
        }
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = dp.length - 1; i >= 0 ; i --){
            for (int j = dp[0].length - 1; j >= 0;j --){
                int down = i+1 < dp.length ? dp[i+1][j] : 0;
                int right = j + 1 < dp[0].length ? dp[i][j + 1] : 0;
                dp[i][j] = Math.max(down,right) + grid[i][j];
            }
        }
        return dp[0][0];
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0){
            return 0;
        }

        int min = prices[0];
        int profit = 0;
        for (int i = 1; i <  prices.length; i ++){
            profit = Math.max(prices[i]  - min,profit);
            min = Math.min(min,prices[i]);
        }
        return min;
    }


    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i ++){
            if (sum <= 0){
                sum = nums[i];
            }else {
                sum = sum + nums[i];
            }
            max = Math.max(sum,max);
        }
        return max;
    }



    public int numWays(int n) {
        if (n == 0){
            return 1;
        }
        if (n <= 2){
            return n;
        }
        int f_1 = 2;
        int f_2 = 1;
        int f_n = 0;

        for (int i = 3; i <= n; i ++){
            f_n = f_1 + f_2;
            f_2 = f_1;
            f_1 = f_n % 1000000007;
        }
        return f_n % 1000000007;
    }

}
