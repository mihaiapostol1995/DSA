package algorithms.trees;

import java.util.Stack;

public class ZigZagTrees {
    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left =  new Node(7);
        tree.left.right = new Node(6);
        tree.right.left = new Node(5);
        tree.right.right = new Node(4);
        
        zigzagTree(tree);
    }

    private static void zigzagTree(Node tree) {
        Stack<Node> stack = new Stack<>();
        Stack<Node> secondStack = new Stack<>();

        secondStack.add(tree);

        while (!stack.isEmpty() || !secondStack.isEmpty()) {
            while (!secondStack.isEmpty()) {
                Node element = secondStack.pop();

                System.out.print(element.value + " ");

                if (element.left != null) stack.push(element.left);
                if (element.right != null) stack.push(element.right);
            }
            while (!stack.isEmpty()) {
                Node element = stack.pop();

                System.out.print(element.value + " ");

                if (element.right != null) secondStack.add(element.right);
                if (element.left != null) secondStack.add(element.left);
            }
        }
    }
}
