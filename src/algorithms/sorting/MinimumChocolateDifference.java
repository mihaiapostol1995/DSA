package algorithms.sorting;

import java.util.Arrays;

public class MinimumChocolateDifference {

    public static void main(String[] args) {
        int[] chocolateArray = {7, 3, 2, 4, 9, 12, 56};
        int students = 3;

        System.out.println(findMinimumDifference(chocolateArray, students));
    }

    private static int findMinimumDifference(int[] arrayOfChocolates, int students) {
        Arrays.sort(arrayOfChocolates);

        int minDifferenceBetweenMaxAndMinChocolates = Integer.MAX_VALUE;
        for (int i = 0; i + students - 1 < arrayOfChocolates.length; i++) {
            minDifferenceBetweenMaxAndMinChocolates =
                    Math.min(minDifferenceBetweenMaxAndMinChocolates, arrayOfChocolates[i + students - 1] - arrayOfChocolates[i]);
        }
        return minDifferenceBetweenMaxAndMinChocolates;
    }
}
