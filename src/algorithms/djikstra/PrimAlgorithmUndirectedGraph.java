package algorithms.djikstra;

import java.util.*;

public class PrimAlgorithmUndirectedGraph {

    public static List<Node> primMST(Node nodeA) {
        List<Node> minimumSpanningTree = new ArrayList<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));

        // Start from an arbitrary node (Assuming it to be the first node)
        nodeA.setDistance(0);
        priorityQueue.offer(nodeA);

        // Continue until the priority queue is empty
        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();

            if (currentNode.isVisited())
                continue;

            currentNode.setVisited(true);
            minimumSpanningTree.add(currentNode);

            // Update the distances of the neighboring nodes
            for (Map.Entry<Node, Integer> entry : currentNode.getOutgoingEdges().entrySet()) {
                Node neighbor = entry.getKey();
                int weight = entry.getValue();
                if (!neighbor.isVisited() && weight < neighbor.getDistance()) {
                    neighbor.setDistance(weight);
                    priorityQueue.offer(neighbor);
                }
            }
        }

        return minimumSpanningTree;
    }

    public static void main(String[] args) {
        // Create nodes
        Node nodeA = new Node("A", Integer.MAX_VALUE);
        Node nodeB = new Node("B", Integer.MAX_VALUE);
        Node nodeC = new Node("C", Integer.MAX_VALUE);
        Node nodeD = new Node("D", Integer.MAX_VALUE);
        Node nodeE = new Node("E", Integer.MAX_VALUE);

        // Create graph with undirected edges and weights
        nodeA.addUndirectedEdge(nodeB, 1);
        nodeB.addUndirectedEdge(nodeC, 2);
        nodeC.addUndirectedEdge(nodeD, 5);
        nodeD.addUndirectedEdge(nodeE, 6);
        nodeE.addUndirectedEdge(nodeA, 3);
        nodeE.addUndirectedEdge(nodeB, 4);

        // Run Prim's algorithm to find the minimum spanning tree
        List<Node> minimumSpanningTree = primMST(nodeA);

        // Print the minimum spanning tree
        System.out.println("Minimum Spanning Tree:");
        for (Node node : minimumSpanningTree) {
            System.out.println(node.getName());
        }
    }
}

class Node {
    private final String name;
    private int distance;
    private final Map<Node, Integer> outgoingEdges;
    private boolean visited;

    public Node(String name, int distance) {
        this.name = name;
        this.distance = distance;
        this.outgoingEdges = new HashMap<>();
        this.visited = false;
    }

    public void addUndirectedEdge(Node node, int weight) {
        outgoingEdges.put(node, weight);
        node.outgoingEdges.put(this, weight);
    }

    public Map<Node, Integer> getOutgoingEdges() {
        return outgoingEdges;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}