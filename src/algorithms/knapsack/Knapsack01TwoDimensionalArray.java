package algorithms.knapsack;

import java.util.Arrays;

public class Knapsack01TwoDimensionalArray {

    //bottom up
    public static void main(String[] args) {
//        int [] weights = {0, 1, 2, 3, 4}; //let this be ALWAYS ordered
//        int [] profits = {5, 2, 10, 11, 3};
        int [] weights = {1, 4, 2};
        int [] profits = {2, 3, 10};

        int maxWeight = 5;
        int items = weights.length;
        int [][] table = new int[items][maxWeight+1];

        knapsack(weights, profits, maxWeight, table);
        System.out.println(Arrays.deepToString(table));
       // System.out.println(knapsackNonRepeatable(weights, profits, maxWeight, items, 0));
    }

    private static void knapsack(int[] weights, int[] profits, int maxWeight, int[][] table) {
        for (int i = 0; i < profits.length; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                if (i == 0) {
                    if (weights[i] <= j) table[i][j] = profits[i];
                    else table[i][j] = 0;
                }

                else if (weights[i] <= j) {
                    table[i][j] = Math.max(profits[i] + table[i - 1][maxWeight - weights[i]],
                            table[i][j - 1]); //the highest sum LOWEST than ours
                }
                else table[i][j] = table[i-1][j];
            }
        }
    }
}
