package algorithms.trees;

import java.util.Stack;

public class MergeTwoBST {
    public static void main(String[] args) {
        /* Let us create the following tree as first tree
             3
            / \
            1 5
        */
//        Node root1  = new Node(3);
//        root1.left = new Node(1);
//        root1.right = new Node(5);

        Node root1 = new Node(10);
        root1.left = new Node(8);
        root1.right = new Node(16);
        root1.left.left = new Node(7);
        root1.left.right = new Node(9);
        root1.right.left = new Node(12);
        root1.right.right = new Node(17);
        root1.left.left.left = new Node(3);

        /* Let us create the following tree as second tree
              4
            / \
            2 6
        */
        Node root2 = new Node(4);
        root2.left = new Node(2);
        root2.right = new Node(6);

        // Print sorted nodes of both trees
        merge(root1, root2);
    }

    private static void merge(Node root1, Node root2) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        stack1.push(root1);
        stack2.push(root2);

        Node current1 = leftTraverse(stack1, stack1.peek());
        Node current2 = leftTraverse(stack2, stack2.peek());

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (stack2.isEmpty() || (!stack1.isEmpty() && current1.value < current2.value)) {
                Node poppedNode = stack1.pop();

                System.out.println(poppedNode.value);
                if (poppedNode.right != null) {
                    stack1.push(poppedNode.right);
                    current1 = leftTraverse(stack1, stack1.peek());
                }
                else if (!stack1.isEmpty()) current1 = stack1.peek();
            }
            else if (stack1.isEmpty() || (!stack2.isEmpty() && current2.value < current1.value)) {
                Node poppedNode = stack2.pop();

                System.out.println(poppedNode.value);
                if (poppedNode.right != null) {
                    stack2.push(poppedNode.right);
                    current2 = leftTraverse(stack2, stack2.peek());
                }
                else if (!stack2.isEmpty()) current2 = stack2.peek();
            }
        }
    }

    public static void mergeBSTs(Node root1, Node root2) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        Node current1 = root1;
        Node current2 = root2;

        // Perform inorder traversal using stacks
        while (current1 != null || current2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {
            // Reach the leftmost node of both trees and push nodes into stacks
            while (current1 != null) {
                stack1.push(current1);
                current1 = current1.left;
            }
            while (current2 != null) {
                stack2.push(current2);
                current2 = current2.left;
            }

            // Pop the top nodes from the stacks
            if (stack2.isEmpty() || (!stack1.isEmpty() && stack1.peek().value <= stack2.peek().value)) {
                current1 = stack1.pop();
                System.out.print(current1.value + " ");

                // Move to the next node in the first BST
                current1 = current1.right;
            } else {
                current2 = stack2.pop();
                System.out.print(current2.value + " ");

                // Move to the next node in the second BST
                current2 = current2.right;
            }
        }
    }

    private static Node leftTraverse(Stack<Node> stack1, Node current1) {
        while (current1.left != null) {
            current1 = current1.left;
            stack1.push(current1);
        }
        return current1;
    }
}
