package algorithms.trees;

import java.util.HashMap;

public class FindDuplicateSubtrees {

    static HashMap<String, Integer> countOfStrings = new HashMap<>();
    public static void main(String[] args) {
        // Root
        Node root = new Node(1);
        
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.right.left = new Node(2);
        root.right.left.left = new Node(4);
        root.right.right = new Node(4);
        
        findAllDuplicates(root);
    }

    private static String findAllDuplicates(Node root) {
        if (root == null) return "#";

        String leftValue = "(" + findAllDuplicates(root.left);
        String rightValue = findAllDuplicates(root.right) + ")";

        String key = leftValue + "," + root.value + "," + rightValue;

        if (countOfStrings.get(key) != null && countOfStrings.get(key) < 2) {
            System.out.println("found duplicate: " + key);
            countOfStrings.put(key, countOfStrings.get(key) + 1);
        }
        else countOfStrings.put(key, 1);

        return key;
    }
}
