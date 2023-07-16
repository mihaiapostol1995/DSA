package algorithms.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// And printing the numbers too!

public class TripletSum {

    public static void main(String[] args) {
        int[] numbers = {1, 4, 2, 7, 3, 9}; //1, 3 and 9
        int targetSum = 13;

        int[] found = findSum(numbers, targetSum, 0, 0);
        System.out.println(Arrays.toString(found));

        System.out.println(Arrays.toString(findTripletSumChatGPT(targetSum, numbers)));
    }

    // My implementation, modified to fit Chat GPT
    private static int[] findSum(int[] array, int sum, int index, int countOfOperations) {
        if (sum == 0) {
            return new int[0]; // return array with 0 as element
        }

        if (countOfOperations == 3 || index >= array.length) {
            return null;
        }

        int[] arrayIncluding = findSum(array, sum - array[index], index + 1, countOfOperations + 1);
        if (arrayIncluding != null) {
            int[] newArray = new int[arrayIncluding.length + 1];
            System.arraycopy(arrayIncluding, 0, newArray, 1, arrayIncluding.length); //initially, the length is 0 so nothing happens

            newArray[0] = array[index];
            return newArray;
        }

        // This will return me either null, or the result if it exists. The ACTUAL operation is done in the 1st part.
        return findSum(array, sum, index + 1, countOfOperations);
    }

    public static int[] findTripletSumChatGPT(int target, int[] arr) {
        Map<String, int[]> memo = new HashMap<>();

        return findTriplet(target, arr, 0, 3, memo);
    }

    // Memoization is interesting here but useless - we never get it from the cache

    private static int[] findTriplet(int target, int[] arr, int index, int count, Map<String, int[]> memo) {
        String key = target + "-" + index + "-" + count;
        if (count == 0 && target == 0) {
            return new int[0];
        }
        if (index >= arr.length || count < 0) {
            return null;
        }

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int[] chosenTriplet = findTriplet(target - arr[index], arr, index + 1, count - 1, memo);
        if (chosenTriplet != null) {
            int[] result = new int[chosenTriplet.length + 1];
            result[0] = arr[index];
            System.arraycopy(chosenTriplet, 0, result, 1, chosenTriplet.length);
            memo.put(key, result);
            return result;
        }

        int[] notChosenTriplet = findTriplet(target, arr, index + 1, count, memo);
        memo.put(key, notChosenTriplet);
        return notChosenTriplet;
    }
}
