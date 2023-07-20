package algorithms.arrays;

public class MaxSumOfNonoverlappingSubarrays {
    public static void main(String[] args) {
//        int subArrayNumber = 3, subArraySize = 1;
//        int[] array = {2, 10, 7, 18, 5, 33, 0};

//        int subArrayNumber = 2, subArraySize = 2;
//        int[] array = {3, 2, 100, 1};

        int subArrayNumber = 2, subArraySize = 3;
        int[] array = {3, 2, 10, 1, 4, 5, 14, 5, 12, 2, 7, 4};

        int[] sumArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < i + subArraySize && j < array.length; j++) {
                sumArray[i] += array[j];
            }
        }
        System.out.println(maxSum(subArrayNumber, sumArray, subArraySize, 0));

        // testing GFG presumarray calculation
        /*
        int presum[] = new int[array.length + 1 - subArraySize] ;
        calculatePresumArray(presum, array, array.length, subArraySize);
         */
    }

    private static int maxSum(int subArrayNumber, int[] array, int subArraySize, int startIndex) {
        if (startIndex >= array.length)
            return 0;
        if (subArrayNumber == 0)
            return 0;

        return Math.max(
                array[startIndex] + // this right here is THE "operation"
                        maxSum(subArrayNumber - 1, array, subArraySize, startIndex + subArraySize),
                maxSum(subArrayNumber, array, subArraySize, startIndex + 1));
    }

    static void calculatePresumArray(int presum[],
                                     int arr[],
                                     int n, int k)
    {
        for (int i = 0; i < k; i++)
            presum[0] += arr[i];

        // store sum of array index i to i+k
        // in presum array at index i of it.
        for (int i = 1; i <= n - k; i++)
            presum[i] += presum[i - 1] + arr[i + k - 1] - // MINUS!
                    arr[i - 1];
    }
}
