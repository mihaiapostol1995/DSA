package algorithms.arrays;

// Java program to find Max sum
// of M non-overlapping subarray
// of size K in an Array


// GFG copy paste, to check my solution
public class MaxSumOfNonoverlappingSubarraysGFG
{
    // calculating presum of array.
// presum[i] is going to store
// prefix sum of subarray of
// size k beginning with arr[i]
    static void calculatePresumArray(int presum[],
                                     int arr[],
                                     int n, int k)
    {
        for (int i = 0; i < k; i++)
            presum[0] += arr[i];

        // store sum of array index i to i+k
        // in presum array at index i of it.
        for (int i = 1; i <= n - k; i++)
            presum[i] += presum[i - 1] + arr[i + k - 1] -
                    arr[i - 1];
    }

    // calculating maximum sum of
    // m non overlapping array
    static int maxSumMnonOverlappingSubarray(int presum[],
                                             int m, int size,
                                             int k, int start)
    {
        // if m is zero then no need
        // any array of any size so
        // return 0.
        if (m == 0)
            return 0;

        // if start is greater then the
        // size of presum array return 0.
        if (start > size - 1)
            return 0;

        int mx = 0;

        // if including subarray of size k
        int includeMax = presum[start] +
                maxSumMnonOverlappingSubarray(presum,
                        m - 1, size, k, start + k);

        // if excluding element and searching
        // in all next possible subarrays
        int excludeMax =
                maxSumMnonOverlappingSubarray(presum,
                        m, size, k, start + 1);

        // return max
        return Math.max(includeMax, excludeMax);
    }

    // Driver code
    public static void main (String[] args)
    {

        int m = 3, k = 1;

        int subArrayNumber = 2, subArraySize = 3;
        int[] array = {3, 2, 10, 1, 4, 5, 14, 5, 12, 2, 7, 4};
        int n = array.length;

        int presum[] = new int[n + 1 - subArraySize] ;
        calculatePresumArray(presum, array, n, subArraySize);

        // resulting presum array
        // will have a size = n+1-k
        System.out.println(maxSumMnonOverlappingSubarray(presum,
                subArrayNumber, n + 1 - subArraySize, subArraySize, 0));
    }
}

// This code is contributed by anuj_67.
