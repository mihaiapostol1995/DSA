package algorithms.trees;

// In binary tree
public class KthSmallestElement {

    static int count = 0;
    public static void main(String[] args) {
        Node tree = new Node(20);
        tree.left = new Node(8);
        tree.right = new Node(22);
        tree.left.left = new Node(4);
        tree.left.right = new Node(12);

        tree.left.right.left = new Node(10);
        tree.left.right.right = new Node(14);

        findKthSmallestNode(tree, 3);
    }

    private static void findKthSmallestNode(Node tree, int key) {
        if (tree == null) return;
        if (count == key) return;

        // We go until the end on the left side. When we reach the null nodes, we will evalue the
        // tree.value against the key only for the last node on the left
        findKthSmallestNode(tree.left, key);
        count++;
        if (count == key) {
            System.out.println("Stopped at tree: " + tree.value);
            return;
        }
        findKthSmallestNode(tree.right, key);
    }
}
