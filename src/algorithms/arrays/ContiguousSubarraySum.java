package algorithms.arrays;

public class ContiguousSubarraySum {

    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        int [] array = {-2, -3, 4, -1, -2, 1, 5, -3};

        for (int i = 0; i < array.length; i++) {
            recursiveSum(array, i, 0);
        }
        System.out.println(max);

        gradualSumBetter(array);

        System.out.println(kadane(array));

        System.out.println(kadaneRecursive(array, 0, array.length - 1));
    }

    private static int kadane(int[] array) {
        int size = array.length;
        int currentMax = Integer.MIN_VALUE, maxEndingHereSincePositiveElement
                = 0;

        for (int i = 0; i < size; i++) {
            // max ending here is always 0 if the previous was negative
            maxEndingHereSincePositiveElement = maxEndingHereSincePositiveElement + array[i];

            if (currentMax < maxEndingHereSincePositiveElement)
                // currentMax is the max at all points of iterating the array
                currentMax = maxEndingHereSincePositiveElement;
            if (maxEndingHereSincePositiveElement < 0) // point of RESET, if the sum is ONLY negative / BECOMES negative
                maxEndingHereSincePositiveElement = 0;
        }

        return currentMax;
    }

    private static int kadaneRecursive(int[] array, int startIndex, int endIndex) {
        if (startIndex == endIndex)
            return array[startIndex];

        int mid = startIndex + (endIndex - startIndex) / 2;

        int maxLeftResult = kadaneRecursive(array, startIndex, mid);
        int maxRightResult = kadaneRecursive(array, mid + 1, endIndex);

        int leftSum = 0, maxLeft = Integer.MIN_VALUE;
        for (int i = mid; i >= startIndex; i--) {
            leftSum += array[i];
            if (leftSum > maxLeft)
                maxLeft = leftSum;
        }

        int rightSum = 0, maxRight = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= endIndex; i++) {
            rightSum += array[i];
            if (rightSum > maxRight)
                maxRight = rightSum;
        }

        return Math.max(Math.max(
                maxLeftResult,
                maxRightResult),
                maxLeft + maxRight);
    }

    private static void gradualSumBetter(int[] array) {
        int sumThusFar = 0, maxSumThusFar = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > sumThusFar + array[i]) {
                sumThusFar = array[i]; // point of reset
            }
            else sumThusFar += array[i];

            if (sumThusFar > maxSumThusFar)
                maxSumThusFar = sumThusFar;
        }

        System.out.println(Math.max(sumThusFar, maxSumThusFar));
    }

    private static void recursiveSum(int[] array, int index, int runningSum) {
        if (index >= array.length) {
            return;
        }

        if (runningSum + array[index] > max)
            max = runningSum + array[index];

        recursiveSum(array, index + 1, runningSum + array[index]);
    }
}
