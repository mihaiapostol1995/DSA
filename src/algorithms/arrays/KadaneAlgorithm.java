package algorithms.arrays;

public class KadaneAlgorithm {
    public static int findMaxSubarraySum(int[] nums) {
        return divideAndConquerChatGPT(nums, 0, nums.length - 1);
    }

    private static int divideAndConquerChatGPT(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;

        int leftMaxSum = divideAndConquerChatGPT(nums, left, mid);
        int rightMaxSum = divideAndConquerChatGPT(nums, mid + 1, right);
        int crossingMaxSum = findCrossingMaxSum(nums, left, mid, right);

        return Math.max(Math.max(leftMaxSum, rightMaxSum), crossingMaxSum);
    }

    private static int findCrossingMaxSum(int[] nums, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;

        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }

        return leftSum + rightSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, -3, 4, -1, -2, 1, 5, -3};
        int maxSum = findMaxSubarraySum(nums);
        System.out.println("Maximum subarray sum: " + maxSum);
    }
}
