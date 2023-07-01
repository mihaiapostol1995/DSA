package algorithms.knapsack;

import java.util.Arrays;

public class Knapsack01OneDimensionalArray {
    public static void main(String[] args) {
        int [] weights = {4, 2, 1};
        int [] profits = {3, 10, 2};

        int maxWeight = 5;
        int[] table = new int[maxWeight+1];

        knapsack(weights, profits, maxWeight, table);
        System.out.println(Arrays.toString(table));
    }

    private static void knapsack(int[] weights, int[] profits, int maxWeight, int[] table) {
        for (int i = 0; i < profits.length; i++) {
            for (int j = maxWeight; j >= 0; j--) {
                if (weights[i] <= j) {
                   // if (table[maxWeight - weights[i]] < weights[i]) {
                        table[j] = Math.max(profits[i] + table[j - weights[i]],
                                table[j]);
                    //}
                }
            }
        }
    }
}
