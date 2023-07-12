package algorithms.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MedianInAStream {

    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(9, 10, 20, 22, 1, 5, 6);

        for (Integer integer : integerList) {
            computeMedianShorter(integer);
        }
    }

    private static void computeMedianShorter(Integer integer) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(integer);
            System.out.println("first median is: " + integer);
        }
        else {
            if (maxHeap.size() - minHeap.size() == 0) {
                minHeap.add(integer);
                maxHeap.add(minHeap.poll());

                System.out.println("median is: " + maxHeap.peek());
            }
            else {
                maxHeap.add(integer);
                minHeap.add(maxHeap.poll());

                int median = (minHeap.peek() + maxHeap.peek())/2;
                System.out.println("computed median on second branch is: " + median);
            }
        }
    }
}
