package algorithms.binarysearch;

public class RotatedArray {
    public static void main(String[] args) {
        int [] array = {9,10,11,12,13,14,15,16,26,30,31,32,5,6,7,8}; //SEARCH 16, index 7
        int key = 16;

        //System.out.println(binarySearch(array, key, 0, array.length - 1));
        System.out.println(binarySearchLogN(array, key, 0, array.length - 1));
    }

    private static int binarySearch(int[] array, int key, int startIndex, int endIndex) {
        int split = (endIndex - startIndex) / 2 + startIndex;
        if (array[split] == key) return split;

        if (endIndex - startIndex == 1  || endIndex - startIndex == 2) {
            if (array[endIndex] == key) return endIndex;
            else if (array[startIndex] == key) return startIndex;
            else return -1;
        }

        if (array[startIndex] < array[endIndex] && key > array[split])
            return binarySearch(array, key, split, endIndex);
        else if (array[startIndex] < array[endIndex] && key < array[split])
            return binarySearch(array, key, 0, split);
        else return Math.max(binarySearch(array, key, 0, split),
                    binarySearch(array, key, split, endIndex));
    }

    private static int binarySearchLogN(int[] array, int key, int startIndex, int endIndex) {
        int split = (endIndex - startIndex) / 2 + startIndex;

        if (array[split] == key) return split;
//        if (endIndex - startIndex == 1  || endIndex - startIndex == 2) {
//            if (array[endIndex] == key) return endIndex;
//            else if (array[startIndex] == key) return startIndex;
//            else return -1;
//        }

        if (array[startIndex] <= key && key <= array[split]) {
            return binarySearchLogN(array, key, startIndex, split);
        }
        else if (array[split] <= key && key <= array[endIndex]) {
            return binarySearchLogN(array, key, split, endIndex);
        }
        else if (array[split] < array[startIndex]) {
            return binarySearchLogN(array, key, startIndex , split);
        }
        else if (array[split] > array[endIndex]) {
            return binarySearchLogN(array, key, split, endIndex );
        }
        else return -1;
    }
//    }
}
