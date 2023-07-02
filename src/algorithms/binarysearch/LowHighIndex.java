package algorithms.binarysearch;

import java.util.Arrays;
import java.util.List;

public class LowHighIndex {
    public static void main(String[] args) {
        int [] array = {1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6};
        int key = 6;

        System.out.println(findLowIndex(array, key, 0, array.length - 1));
        System.out.println(findLowIndex(Arrays.asList(1,1,2,2,2,2), 2));
    }

    private static int findLowIndex(int[] array, int key, int startIndex, int endIndex) {
        int split = (endIndex - startIndex) / 2 + startIndex;

        if (array[split] == key) {
             if (array[split - 1] == key) {
                 return findLowIndex(array, key, startIndex, split - 1); //keep looking
             }
             else if (array[split - 1] != key) return split;
        }
        else if (array[split] < key) {
            return findLowIndex(array, key, split + 1, endIndex);
        }
        else if (array[split] > key) {
            return findLowIndex(array, key, startIndex, split - 1);
        }
        return -1;
    }

    static int findLowIndex(List<Integer> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;
        int mid = high / 2;

        while (low <= high) {

            int mid_elem = arr.get(mid);

            if (mid_elem < key) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }

            mid = low + (high - low) / 2;
        }

        if (low < arr.size() && arr.get(low) == key) {
            return low;
        }

        return -1;
    }
}
