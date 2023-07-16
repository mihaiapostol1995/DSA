package algorithms.miscellaneous;

import java.util.Arrays;

// Using two pointers technique
public class TripletSum {
    public static void main(String[] args) {
        int[] numbers = {1, 4, 2, 7, 3, 9}; //1, 3 and 9
        Arrays.sort(numbers); // This is VERY IMPORTANT!!! Otherwise, you'll have random results
        int targetSum = 13;

        int[] found = twoPointers(numbers, targetSum);
        System.out.println(Arrays.toString(found));
    }

    private static int[] twoPointers(int[] numbers, int targetSum) {
        for (int i = 0; i < numbers.length; i++) {
            int runningSum = targetSum - numbers[i];

            int j = 0, k = numbers.length - 1;
            while (j != k) {
                int intermediarySum = numbers[k] + numbers[j];

                if (intermediarySum == runningSum)
                    return new int[]{numbers[i], numbers[j], numbers[k]};
                if (intermediarySum < runningSum) j++;
                else k--;
            }
        }
        return new int[0];
    }
}
