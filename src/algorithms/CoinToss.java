package algorithms;

import java.util.Arrays;

public class CoinToss {

    public static int counts = 0;
    public static int callCount = 0;
    public static int beginnerCount = 0;

    public static void main(String[] args) {
        int sum = 20;
        int[] coins = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};

        computeCombinations(sum, coins, coins[0]);
        int results = count(sum, coins, coins.length);

        System.out.println(counts);
        System.out.println(results);

        int[][] dp = new int[coins.length + 1][sum + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        System.out.println(coinChange(coins, sum, coins.length, dp));
        System.out.println(Arrays.deepToString(dp));

        //System.out.println(callCount);
        System.out.println(beginnerCount);
    }

    private static int count(int sum, int[] coins, int length) {
        if (sum < 0) return 0;
        if (sum == 0) return 1;
        if (length == 0) return 0;

        beginnerCount++;
        return count(sum - coins[coins.length - length], coins, length) //solve the problem
                + count(sum, coins, --length); //delegate the problem
    }

    private static void computeCombinations(int sum, int[] coins, int currentCoin) {
        for (int coin : coins) {
            if (coin < currentCoin) continue;
            int temporarySum = sum - coin;

            if (temporarySum < 0) return;
            else if (temporarySum == 0) {
                counts++;
                return;
            }
            else computeCombinations(temporarySum, coins, coin);
        }
    }

    private static void countsTable(int sum, int[] coins, int currentCoin) {

    }

    static int coinChange(int[] coins, int remainingSum, int arrayLength, int[][] dp) {
        if (remainingSum < 0) return 0;

        if (dp[arrayLength][remainingSum] != -1)
            return dp[arrayLength][remainingSum]; //This is the caching part of the algorithm

        if (remainingSum == 0) return 1;
        if (arrayLength == 0) return 0;

 //       if (coins[arrayLength - 1] <= remainingSum) {
            // Either Pick this coin or not
            return dp[arrayLength][remainingSum]
                    = coinChange(coins, remainingSum - coins[arrayLength - 1], arrayLength, dp)
                    + coinChange(coins, remainingSum, arrayLength - 1, dp);
//        }
//        else // We have no option but to leave this coin
//            return dp[arrayLength][remainingSum] = coinChange(coins, remainingSum, arrayLength - 1, dp);
    }
}
