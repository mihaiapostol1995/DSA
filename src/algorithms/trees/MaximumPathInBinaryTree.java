package algorithms.trees;

// I DON'T think this is a binary search tree, the nodes seem disordered. It's a regular tree.
public class MaximumPathInBinaryTree {

    static int maxPathSum = Integer.MIN_VALUE;
    public static void main(String[] args) {
        Node tree = new Node(10);
        tree.left = new Node(2);
        tree.right = new Node(10);
        tree.left.left = new Node(20);
        tree.left.right = new Node(1);
        tree.right.right = new Node(-25);
        tree.right.right.left = new Node(3);
        tree.right.right.right = new Node(4);

        // Function call
        System.out.println("maximum path sum is : " + findMaxSum(tree, tree));

        System.out.println("maximum path sum is : " + findMaxSumGPT(tree));

        findMaxPathSumHelper(tree);
        System.out.println(maxPathSum);
    }

    private static Integer findMaxSum(Node root, Node initialRoot) {
        if (root == null) return 0;

        if (root == initialRoot) return Math.max(Math.max(Math.max(
                root.value,
                findMaxSum(root.left, initialRoot) + root.value),
                findMaxSum(root.right, initialRoot) + root.value),
                findMaxSum(root.right, initialRoot) + root.value + findMaxSum(root.left, initialRoot));
        else return Math.max(Math.max(
                        root.value,
                        findMaxSum(root.left, initialRoot) + root.value),
                findMaxSum(root.right, initialRoot) + root.value);
    }

    private static Integer findMaxSumGPT(Node root) {
        if (root == null) return 0;

        int leftSideOfTree = Math.max(0, findMaxSumGPT(root.left));
        int rightSideOfTree = Math.max(0, findMaxSumGPT(root.right));

        return root.value + Math.max(leftSideOfTree, rightSideOfTree);
    }

    private static int findMaxPathSumHelper(Node node) {
        if (node == null)
            return 0;

        // Recursively calculate the maximum path sum for the left and right subtrees
        int leftSum = Math.max(0, findMaxPathSumHelper(node.left));
        int rightSum = Math.max(0, findMaxPathSumHelper(node.right));

        // Update the maximum path sum if the current node is part of the path
        maxPathSum = Math.max(maxPathSum, node.value + leftSum + rightSum);

        // Return the maximum sum of the current node with either its left or right subtree
        return node.value + Math.max(leftSum, rightSum);
    }
}
