package algorithms.knapsack;

public class Knapsack01 {
    public static void main(String[] args) {
        int [] weights = {1, 4, 2};
        int [] profits = {2, 3, 10};
        int maxWeight = 5;
        int items = weights.length;

        int maxProfit = knapsack(weights, profits, maxWeight, items, 0);
        //System.out.println(maxProfit);
        System.out.println(knapsackNonRepeatable(weights, profits, maxWeight, items, 0));
    }

    private static int knapsack(int[] weights, int[] profits, int maxWeight, int items, int profit) {
        if (items <= 0) return profit; //no items left
        if (maxWeight == 0) return profit;
        if (maxWeight < 0) { //can only come after subtraction of weights[items-1]
            return profit - profits[items-1];
        }

        return Math.max(knapsack(weights, profits, maxWeight - weights[items-1], items, profit + profits[items-1]),
                knapsack(weights, profits, maxWeight, items-1, profit));
    }

    private static int knapsackNonRepeatable(int[] weights, int[] profits, int maxWeight, int items, int profit) {
        if (items <= 0) {
            return profit; //no items left
        }
        if (maxWeight == 0) {
            return profit;
        }
        if (maxWeight < 0) { //can only come after subtraction of weights[items-1]
            return profit - profits[items-1];
        }

        return Math.max(knapsackNonRepeatable(weights, profits, maxWeight - weights[items-1], items-1, profit + profits[items-1]),
                knapsackNonRepeatable(weights, profits, maxWeight, items-1, profit));
    }
}
