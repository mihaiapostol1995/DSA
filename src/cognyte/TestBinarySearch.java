package cognyte;

import java.util.Arrays;

public class TestBinarySearch {
    public static void main(String[] args) {
        int result = Arrays.binarySearch(new int[]{0,1,2,4,5,6,7}, 3);
        System.out.println(result);
    }
}
