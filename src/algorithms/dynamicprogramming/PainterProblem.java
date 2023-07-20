package algorithms.dynamicprogramming;

public class PainterProblem {
    public static void main(String[] args) {
        int[] paintingBoards = { 10, 20, 60, 50, 30, 40 };
        int painters = 3;

        System.out.println(findMinimumTime(paintingBoards, painters, 0, 0, 0));
    }

    private static int findMinimumTime(int[] array, int painters, int startIndex, int runningTotal, int previousMaxTime) {
        if (startIndex >= array.length && painters <= 0)
            return Math.max(previousMaxTime, runningTotal);
        else if (startIndex >= array.length || painters <= 0)
            return Integer.MAX_VALUE; // we terminated too early, this value doesn't "count"

        // runningTotal = array[startIndex];
        else return Math.min(
                findMinimumTime(array, painters, startIndex + 1,
                        runningTotal + array[startIndex], Math.max(runningTotal + array[startIndex], previousMaxTime)),
                findMinimumTime(array, painters - 1, startIndex + 1,
                        array[startIndex], Math.max(array[startIndex], previousMaxTime))
        );
    }
}
