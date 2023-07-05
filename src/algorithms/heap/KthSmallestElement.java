package algorithms.heap;

import java.util.PriorityQueue;

public class KthSmallestElement {
    public static void main(String[] args) {

        int[][] matrix = { { 10, 20, 30, 40 },
                { 15, 25, 35, 45 },
                { 25, 29, 37, 48 },
                { 32, 33, 39, 50 } };

        int k = 4;
        findKthSmallestElement(matrix, k);
    }

    private static void findKthSmallestElement(int[][] matrix, int k) {
        PriorityQueue<Node> smallestElement = new PriorityQueue<>();

        for (int rowValue = 0 ; rowValue < matrix[0].length; rowValue++) {
            smallestElement.add(new Node(rowValue, 0, matrix[rowValue][0]));
        }

        for (int i = 0; i < k; i++) {
            Node smallestNode = smallestElement.poll();

            int column = smallestNode.columnValue;
        }
    }

    static class Node implements Comparable<Node> {
        int rowValue;
        int columnValue;
        int value;

        public Node(int rowValue, int columnValue, int value) {
            this.rowValue = rowValue;
            this.columnValue = columnValue;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }
}
