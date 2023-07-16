package algorithms.binarysearch;

public class FindIndexLocation {
    public static void main(String[] args) {
        int[] array = {1, 2, 4, 5};
        int key = 3;

        System.out.println(binarySearchMissingElement(array, key));
    }

    private static int binarySearchMissingElement(int[] array, int key) {
        int low = 0, high = array.length;
        int middle = (high - low) / 2;

        while (low <= high) {
            if (array[middle] == key) return middle;
            else if (key > array[middle]) low = middle + 1;
            else high = middle - 1;

            middle = low + (high - low) / 2;
        }

        return low;
    }
}
