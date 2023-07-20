package algorithms.linkedlist;

public class DetectLoop {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.head = new LinkedList.Node(1);
        linkedList.head.next = new LinkedList.Node(2);
        linkedList.head.next.next = new LinkedList.Node(3);
        linkedList.head.next.next.next = new LinkedList.Node(4);
        linkedList.head.next.next.next.next = new LinkedList.Node(5);

        // Create a loop for testing (5 is pointing to 3) /
        linkedList.head.next.next.next.next.next = linkedList.head.next.next;
        
        detectLoopFloyd(linkedList);

        detectLoopRemovingNext(linkedList);
        linkedList.print(linkedList.head); // All the nodes' "next" relation will be broken
    }

    private static boolean detectLoopRemovingNext(LinkedList linkedList) {
        LinkedList.Node current = linkedList.head;

        // in the end, ALL nodes will be pointing to this node! Not nice solution
        LinkedList.Node temp = new LinkedList.Node(0);
        while (current != null) {
            if (current.next == temp) {
                System.out.println("found loop: " + current.data);
                return true;
            }

            if (current.next == null) {
                System.out.println("no loop detected");
                return false;
            }

            LinkedList.Node nextNode = current.next; // "save" the next node
            current.next = temp;

            current = nextNode; // move to the next node that we saved
        }

        return false;
    }

    private static void detectLoopFloyd(LinkedList linkedList) {
        LinkedList.Node current = linkedList.head;
        LinkedList.Node nextNode = linkedList.head;

        while (current != null && nextNode != null) {
            current = current.next;
            nextNode = nextNode.next.next;
            if (current == nextNode) {
                System.out.println("found loop: " + current.data);
                return;
            }
        }
        System.out.println("no found loop");
    }
}
