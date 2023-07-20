package algorithms.linkedlist;

import sun.plugin.cache.CacheUpdateHelper;

import java.util.Stack;

public class ReverseLinkedList {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.head = new LinkedList.Node(85);
        list.head.next = new LinkedList.Node(15);
        list.head.next.next = new LinkedList.Node(4);
        list.head.next.next.next = new LinkedList.Node(20);

        list.print(list.head);
        LinkedList.Node newHead = list.reverse();
        list.print(newHead);

        newHead = list.recurse(newHead, null);
        list.print(newHead);

        newHead = list.reverseWithStack(newHead);
        list.print(newHead);
    }
}

class LinkedList {

    Node head;

    public Node reverse() {
        Node previous = null, current = head;
        while (current != null) {
            Node actualNextNode = current.next; // retrieve the current node before reversing
            current.next = previous; // reverse the linked list

            previous = current; // PREPARE FOR THE NEXT STEP! WE NEED TO REFERENCE THE NEXT BACK TO THE PREVIOUS!
            current = actualNextNode; // get back what we stored in the 1st place
        }
        return previous;
    }

    public void print(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(" " + current.data);
            current = current.next;
        }
        System.out.println();
    }

    public Node recurse(Node current, Node previous) {
        if (current == null)
            return previous;

        Node next = current.next; // save the next to be later used
        current.next = previous; // this is where we reverse
        return recurse(next, current);
    }

    public LinkedList.Node reverseWithStack(Node head) {
        Stack<LinkedList.Node> nodes = new Stack<>();

        Node current = head;
        while (current.next != null) {
            nodes.push(current);
            current = current.next;
        }
        nodes.push(current);

        while (!nodes.isEmpty()) {
            Node currentNode = nodes.pop();
            currentNode.next = nodes.isEmpty() ? null : nodes.peek();
        }

        return current;
    }

    static class Node {

        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }
}
