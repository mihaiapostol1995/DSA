package algorithms.dynamicprogramming;

public class PainterProblem {
    public static void main(String[] args) {
        int[] paintingBoards = { 10, 20, 60, 50, 30, 40 };
        int painters = 3;

        //System.out.println(findMinimumTime(paintingBoards, painters, 0, 0, 0));

        System.out.println(findMinimumTimeDynamicProgramming(paintingBoards, painters));
        System.out.println(findMinimumTime(paintingBoards, painters - 1, 0, paintingBoards.length - 1));
    }

    /*
    private static int findMinimumTime(int[] array, int painters, int startIndex, int runningTotal, int previousMaxTime) {
        if (startIndex >= array.length && painters <= 0)
            return Math.max(runningTotal, previousMaxTime); // we return all computations of the "Max" made thus
        else if (startIndex >= array.length || painters <= 0)
            return Integer.MAX_VALUE; // we terminated too early, this value doesn't "count"

        // runningTotal = array[startIndex];
        else return Math.min(
                findMinimumTime(array, painters, startIndex + 1,
                        runningTotal + array[startIndex], Math.max(runningTotal + array[startIndex], previousMaxTime)), // the "operation"
                findMinimumTime(array, painters - 1, startIndex + 1,
                        array[startIndex], Math.max(array[startIndex], previousMaxTime))
        );
        // the "running total" is a trick, that the GFG article is not using, they instead compute the sum
    }
     */

    // all recursive solutions are "memoizations". Meaning, top-down approach! This is exactly why this approach is slow compared to bottom-up 2D approach
    private static int findMinimumTime(int[] array, int painters, int begin, int end) {
        if (painters <= 0)
            return sum(array, begin, end);

        int maxTime = Integer.MAX_VALUE;
        for (int i = begin; i <= end; i++ ) { // you "slide" the partition point
            maxTime = Math.min(maxTime,
                    Math.max(findMinimumTime(array, painters - 1, i + 1, end), //partition
                            sum(array, begin, i))); // do the "operation" inside the for-loop operation
        }
        return maxTime;
    }

    private static int findMinimumTimeDynamicProgramming(int[] array, int painters) {
        int [][] dp = new int[painters][array.length];

        // two base cases
        dp[0][0] = array[0];
        for (int i = 1; i < array.length; i++)
            dp[0][i] = array[i] + dp[0][i - 1];

        for (int i = 0; i < painters; i++)
            dp[i][0] = array[0];

        for (int i = 1; i < painters; i++) {
            for (int j = 1; j < array.length; j++) {
                int maxTime = Integer.MAX_VALUE;

                // we NEED this inner for loop... this is the only situation where the "operation" in fact INCLUDES a for-loop!
                for (int k = 1; k <= j; k++) {
                    maxTime = Math.min(maxTime,
                            Math.max(dp[i - 1][k - 1],
                                    sum(array, k, j)));
                }

                dp[i][j] = maxTime;
            }
        }

        return dp[painters - 1][array.length - 1];
    }

    private static int sum(int[] array, int startIndex, int endIndex) {
        int sum = 0;
        for (int i = startIndex; i <= endIndex; i++) { //or equals because we need the middle too!
            sum += array[i];
        }
        return sum;
    }
}
