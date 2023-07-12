package algorithms.dynamicprogramming;

public class TripletSum {
    public static void main(String[] args) {
        int[] numbers = {1, 4, 2, 7, 3, 9};
        int targetSum = 13;
        int [][] dp = new int[numbers.length][numbers.length];

        boolean found = findSum(numbers, targetSum, 0, 0, dp);
        System.out.println(found);
    }

    private static boolean findSum(int[] array, int sum, int index, int countOfOperations, int [][] dp) {
        if (sum == 0) return true;

        if (countOfOperations == 3 || index >= array.length) {
            return false;
        }

        //dp[index][index + 1] = sum - array[index];

        return findSum(array, sum - array[index], index + 1, countOfOperations + 1, dp)
                || findSum(array, sum, index + 1, countOfOperations, dp);
    }
}
