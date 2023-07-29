package algorithms.sorting;

public class FloorInArray {

    public static void main(String[] args) {
//        int[] sortedArray = {1, 2, 8, 10, 10, 12, 19};
//        int x = 5;

        int sortedArray[] = {1, 2, 8, 10, 10, 12, 19}, x = 20;
        System.out.println(floor(sortedArray, x));
    }

    private static int floor(int[] sortedArray, int x) {
        int start = 0, end = sortedArray.length - 1;
        int middle = end / 2;
        while (start <= end) {
            if (x == sortedArray[middle])
                return middle;
            else if (x > sortedArray[middle])
                start = middle + 1;
            else end = middle - 1;

            middle = start + (end - start) / 2; //redo the computation
        }

        return sortedArray[middle - 1];
    }
}
