package algorithms.stack;

import java.util.Stack;

public class LargestRectangularAreaHistogram {
    public static void main(String[] args) {
        //int[] histogram = {6, 2, 5, 4, 5, 1, 6};
        //int[] histogram = {6, 5, 5};
        //int[] histogram = {3, 5, 1, 7, 5, 9};

        int[] histogram = {3,4,4,2};

        System.out.println(calculateLargestArea(histogram));
        System.out.println(largestRectangleArea(histogram));
    }

    // sort-of opposite to the two-pointer technique (if i can even compare them, since two-pointer works for sorted arrays)
    private static int calculateLargestArea(int[] histogram) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < histogram.length; i++) {
            int count = 0;

            if (i == 0) {
                for (int j = 0; j < histogram.length; j++) {
                    if (histogram[j] < histogram[i])
                        break;
                    else count++;
                }
                max = Math.max(max, count * histogram[i]);
                continue;
            }

            // go left
            for (int j = i - 1; j > -1; j--) {
                if (histogram[j] < histogram[i])
                    break;
                else count++;
            }

            //go right
            for (int j = i; j < histogram.length; j++) {
                if (histogram[j] < histogram[i])
                    break;
                else count++;
            }
            max = Math.max(max, count * histogram[i]);
        }
        return max;
    }


    // Chat GPT
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= heights.length; i++) {
            while (!stack.isEmpty()
                    // for equal elements in the array one after the other, use "<="
                    && (i == heights.length || heights[i] <= heights[stack.peek()])) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }
}
