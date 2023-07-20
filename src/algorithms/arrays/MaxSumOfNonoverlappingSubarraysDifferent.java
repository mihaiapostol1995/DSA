package algorithms.arrays;


// Chat GPT inspired, because he used dynamic programming
public class MaxSumOfNonoverlappingSubarraysDifferent {
    public static void main(String[] args) {
        int subArrayNumber = 2, subArraySize = 3;
        int[] array = {3, 2, 10, 1, 4, 5, 14, 5, 12, 2, 7, 4};

        System.out.println(maxSumMihai2DArray(subArrayNumber, subArraySize, array));
        System.out.println(maxSumSubarraysChatGPT(array, 2, 3));

        maxSumMihai1DArray(subArrayNumber, subArraySize, array);
    }

    private static int maxSumMihai2DArray(int subArrayNumber, int subArraySize, int[] array) {
        int[][] dp = new int[2][array.length];
        int[] sumUntil = new int[array.length];

        // fill in the sum of all numbers till i;
        sumUntil[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            sumUntil[i] = sumUntil[i -1] + array[i];
        }

        // base cases
        for (int m = 0; m < subArrayNumber; m++) {
            int M = m + 1;
            dp[m][subArraySize] = sumUntil[subArraySize * M - 1];
        }

        // for EACH count of subarray (1, 2, etc subarrays), we find the max for K elements (where K is the size of the subarray)
        for (int m = 0; m < subArrayNumber; m++) {

            int M = m + 1;
            for (int j = subArraySize * M + 1; j < array.length; j++) {
                int operationResult = m == 0
                        ? sumUntil[j] - sumUntil[j - subArraySize]
                        : dp[m - 1][j - subArraySize] + sumUntil[j] - sumUntil[j - subArraySize];

                dp[m][j] = Math.max(dp[m][j - 1], //recurse back
                        operationResult); // this is "THE OPERATION"
            }

        }

        return dp[1][array.length - 1];
    }

    // Doesn't work!
    private static int maxSumMihai1DArray(int subArrayNumber, int subArraySize, int[] array) {
        int[] dp = new int[array.length];
        int[] sumUntil = new int[array.length];

        sumUntil[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            sumUntil[i] = sumUntil[i -1] + array[i];
        }

        // base cases
        int arrayLength = array.length;
        for (int m = 1; m <= subArrayNumber; m++) {
            dp[arrayLength - subArraySize * m] =
                    sumUntil[arrayLength - 1] - sumUntil[arrayLength - subArraySize * m - 1];
        }

        // for EACH count of subarray (1, 2, etc subarrays), we find the max for K elements (where K is the size of the subarray)
        for (int m = 1; m <= subArrayNumber; m++) {
            // -1 because we already took care of the base case!
            for (int j = arrayLength - subArraySize * m - 1; j >= 0; j--) {
                int add = j == 0 ? 0 : sumUntil[j - 1];
                int operation = sumUntil[j + subArraySize - 1] - add;

                dp[j] = Math.max(dp[j + 1], // recurse back
                        m == 1 ? operation : (dp[j + subArraySize] + operation)); // the "operation"
            }
            // restore the base case for the next iteration
            dp[arrayLength - subArraySize * (m + 1)] =
                    sumUntil[arrayLength - 1] - sumUntil[arrayLength - subArraySize * (m + 1) - 1];
        }

        return dp[0];
    }

    public static int maxSumSubarraysChatGPT(int[] nums, int M, int K) {
        int[] prefixSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int[] dp = new int[nums.length];
        for (int m = 0; m < M; m++) {
            for (int i = nums.length - 1; i >= K * (m + 1) - 1; i--) {
                dp[i] = Math.max(dp[i],
                        prefixSum[i + 1] - prefixSum[i - K + 1] + (m > 0 ? dp[i - K] : 0)); // good idea, to start from the end
            }
        }

        return dp[nums.length - 1];
    }
}
