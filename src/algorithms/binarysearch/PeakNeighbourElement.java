package algorithms.binarysearch;

public class PeakNeighbourElement {
    public static void main(String[] args) {
        int[] array = {5, 10, 20, 25};

        System.out.println(findPeakElement(array, 0, array.length - 1, (array.length - 1) / 2));
        System.out.println(findPeakElementSmarter(array, 0, array.length - 1));
        System.out.println(findPeakElementSmarterWhileLoop(array, 0, array.length - 1));
    }

    private static int findPeakElement(int[] array, int start, int end, int middle) {
        if (start > end)
            return -1;

        if (middle == 0) {
            if (array[middle + 1] < array[middle])
                return middle;
        }
        else if (middle == array.length - 1) {
            if (array[middle] > array[middle - 1])
                return middle;
        }
        else if (array[middle] > array[middle - 1] && array[middle] > array[middle + 1])
            return middle;

        middle = start + (end - start) / 2;
        int result = findPeakElement(array, middle + 1, end, middle + 1 + (end - middle - 1) / 2);
        return result > 0 ? result : findPeakElement(array, start, middle, start + (middle - start) / 2);
    }

    private static int findPeakElementSmarter(int[] array, int start, int end) {
        /*
        if (middle == 0) {
            if (array[middle + 1] <= array[middle])
                return 0;
        }
        else if (middle == array.length - 1) {
            if (array[middle] >= array[middle - 1])
                return array.length - 1;
        }
        else if (array[middle] >= array[middle - 1] && array[middle] >= array[middle + 1])
            return middle;
         */

        // also this is a nicer approach then computing the middle every time... repetitive code!
        int middle = start + (end - start) / 2;

        // nicer approach! thanks GFG
        if ((middle == 0 || array[middle] >= array[middle - 1])
            && ((middle == array.length - 1) || array[middle] >= array[middle + 1]))
            return middle;

        if (array[middle] > array[middle - 1])
            return findPeakElementSmarter(array, middle + 1, end);
        else return findPeakElementSmarter(array, start, middle - 1);
    }

    private static int findPeakElementSmarterWhileLoop(int[] array, int start, int end) {
        int middle = (end - start) / 2;

        while (start <= end) {
            // nicer approach! thanks GFG
            // find the peak at each iteration (there can be MANY possible peaks!)
            if ((middle == 0 || array[middle] >= array[middle - 1])
                    && ((middle == array.length - 1) || array[middle] >= array[middle + 1]))
                break; //again, THANKS GFG! smarter this way, break and return, so I don't think about "what should I return in case nothing is found, which is impossible"

            if (array[middle] > array[middle - 1])
                start = middle + 1;
            else end = middle - 1;

            middle = start + (end - start) / 2;
        }
        return middle;
    }
}
