package algorithms.djikstra;

import java.util.*;

//Djikstra implementation using Node("A", distance), and a PriorityQueue and a HashMap that contains the distances
public class DijkstraAlgorithmDirected {

    public static void dijkstra(Node source) {
        // Create a map to store distances of nodes from the source
        Map<Node, Integer> distances = new HashMap<>();

        // Initialize distances of all nodes to infinity
        for (Node node : source.getGraph().keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }

        // Distance from source to itself is always the starting distance
        distances.put(source, source.getDistance());

        // Create a priority queue to store nodes with their distances
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        priorityQueue.offer(source);

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll(); //This also removes the node

            // Update distances of adjacent nodes
            for (Map.Entry<Node, Integer> neighbor : current.getGraph().entrySet()) {
                Node adjacentNode = neighbor.getKey();
                int weight = neighbor.getValue();

                int distance = distances.get(current) + weight;
                if (distance < distances.get(adjacentNode)) {
                    distances.put(adjacentNode, distance);
                    priorityQueue.offer(adjacentNode);
                }
            }
        }

        // Print the shortest distances from the source node
        printShortestDistances(distances);
    }

    private static void printShortestDistances(Map<Node, Integer> distances) {
        System.out.println("Shortest distances from the source node:");
        for (Map.Entry<Node, Integer> entry : distances.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            System.out.println("Node " + node.getName() + ": " + distance);
        }
    }

    public static void main(String[] args) {
        // Create nodes with starting distances (these are actually weights)
        Node nodeA = new Node("A", 0);
        Node nodeB = new Node("B", 4);
        Node nodeC = new Node("C", 2);
        Node nodeD = new Node("D", 3);
        Node nodeE = new Node("E", 1);

        // Create graph with distances between nodes
        nodeA.addEdge(nodeB, 4);
        nodeA.addEdge(nodeC, 2);
        nodeB.addEdge(nodeD, 5);
        nodeC.addEdge(nodeD, 7);
        nodeC.addEdge(nodeE, 1);
        nodeD.addEdge(nodeE, 3);

        // Run Dijkstra's algorithm from a source node
        dijkstra(nodeA);
    }

    static class Node {

        private final String name;
        private final int distance; //Is this the actual WEIGHT of the edge? Is it is, check add method.

        //Each node has a certain weight associated with its distance from THIS node. This is the edge weight
        private final Map<Node, Integer> graph;

        public Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
            this.graph = new HashMap<>();
        }

        public String getName() {
            return name;
        }

        public int getDistance() {
            return distance;
        }

        public void addEdge(Node node, int weight) {
            graph.put(node, weight);
        }

        public Map<Node, Integer> getGraph() {
            return graph;
        }
    }
}
