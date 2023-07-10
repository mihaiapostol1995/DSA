package algorithms.trees;


public class MaximumDepthOfTree {
    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);

        System.out.println("Height of tree is: " + maxDepth(tree, 0));
        System.out.println(maxDepthNoParam(tree));
    }

    private static int maxDepth(Node tree, int depth) {
        if (tree == null) return depth;

        depth++;

        return Math.max(maxDepth(tree.left, depth), maxDepth(tree.left, depth));
    }

    private static int maxDepthNoParam(Node tree) {
        if (tree == null) return 0;

        return Math.max(maxDepthNoParam(tree.left),
                maxDepthNoParam(tree.left)) + 1;
        // Cleaner!!! "+1" represents the typical recursion operation
    }
}
