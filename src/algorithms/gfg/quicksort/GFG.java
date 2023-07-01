package algorithms.gfg.quicksort;// Java implementation of QuickSort

import java.util.Arrays;

class GFG {

    // A utility function to swap two elements
    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // This function takes last element as pivot,
    // places the pivot element at its correct position
    // in sorted array, and places all smaller to left
    // of pivot and all greater elements to right of pivot
    static int partition(int[] arr, int lowestIndex, int highestIndex)
    {
        // Choosing the pivot
        int pivot = arr[highestIndex];

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (lowestIndex - 1);

        for (int j = lowestIndex; j <= highestIndex - 1; j++) {

            // If current element is smaller than the pivot
            if (arr[j] < pivot) {

                // Increment index of smaller element, this will be reflected after the swap
                i++;
                swap(arr, i, j); //WHY
            }
        }
        swap(arr, i + 1, highestIndex); //WHY
        return (i + 1);
    }

    // The main function that implements QuickSort
    // arr[] --> Array to be sorted,
    // low --> Starting index,
    // high --> Ending index
    static void quickSort(int[] arr, int low, int high)
    {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    // Driver Code
    public static void main(String[] args)
    {
       // int[] arr = { 10, 7, 8, 9, 1, 5 };

        //int [] arr = {5,1,2,3,4};
        //int [] arr = {1,2,4,3,5};
        //int [] arr = {1,2,3,5,4};
        int [] arr = {5,4,2,1,3};
        int N = arr.length;

        // Function call
        quickSort(arr, 0, N - 1);
        System.out.println(Arrays.toString(arr));
    }
}

// This code is contributed by Ayush Choudhary
// Improved by Ajay Virmoti