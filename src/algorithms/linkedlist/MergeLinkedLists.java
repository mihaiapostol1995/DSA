package algorithms.linkedlist;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class MergeLinkedLists {

    public static void main(String[] args) {
        // Create the first sorted linked list: 1 -> 3 -> 5
        Node head1 = new Node(1);
        head1.next = new Node(3);
        head1.next.next = new Node(5);

        // Create the second sorted linked list: 2 -> 4 -> 6
        Node head2 = new Node(2);
        head2.next = new Node(4);
        head2.next.next = new Node(6);

        // Merge the two lists and obtain the merged list head

        // Print the merged list
        Node current = mergeListsInPlace(head1, head2);
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        // Output: 1 2 3 4 5 6
    }

    private static Node mergeLists(Node head1, Node head2) {
        Node root, copiedRoot;

        Node current1, current2;
        if (head1.data < head2.data) {
            root = new Node(head1.data);
            copiedRoot = root;

            current1 = head1.next;
            current2 = head2;
        }
        else {
            root = new Node(head2.data);
            copiedRoot = root;

            current2 = head2.next;
            current1 = head1;
        }

        while (current1 != null || current2 != null) {
            if (current1 != null && current1.data < current2.data) {
                copiedRoot.next = new Node(current1.data);
                copiedRoot = copiedRoot.next;

                current1 = current1.next;
            }
            else {
                copiedRoot.next = new Node(current2.data);
                copiedRoot = copiedRoot.next;

                current2 = current2.next;
            }
        }

        return root;
    }

    private static Node mergeListsInPlace(Node head1, Node head2) {
        Node root;

        if (head1.data < head2.data) {
            root = head1;
            head1 = head1.next;;
        }
        else {
            root = head2;
            head2 = head2.next;
        }

        Node copiedRoot = root; // Pointer to the current node in the merged list

        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                copiedRoot.next = head1;
                copiedRoot = copiedRoot.next;

                head1 = head1.next;
            }
            else {
                copiedRoot.next = head2;
                copiedRoot = copiedRoot.next;

                head2 = head2.next;
            }
        }

        if (head1 == null) {
            copiedRoot.next = head2;
        }
        if (head2 == null) {
            copiedRoot.next = head1;
        }

        return root;
    }
}
