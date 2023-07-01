package algorithms.slidingwindow;

public class MyAttempt {
    public static void main(String[] args) {
        int[] array = {4,5,1,5};
        int k = 2;

        System.out.println(findMaxSubarray(array, k));
    }

    private static int findMaxSubarray(int[] array, int k) {
        int intermediarySum = 0;
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (j < k) {
                intermediarySum += array[i];
                j++;
            }
            else {
                int anotherSum = intermediarySum;
                anotherSum = anotherSum + array[i] - array[i-k];
                if (anotherSum > intermediarySum) intermediarySum = anotherSum;
            }
        }
        return intermediarySum;
    }
}
