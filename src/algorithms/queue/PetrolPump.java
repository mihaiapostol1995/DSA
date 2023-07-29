package algorithms.queue;

import java.util.LinkedList;
import java.util.Queue;

public class PetrolPump {
    public static void main(String[] args) {
        // 1st variable is the petrol quantity, 2nd one is the distance till the next pump
         int[][] petrolPumps = {{4, 6}, {3, 5}, {2, 3}, {10, 5}};

        //int[][] petrolPumps = {{4, 6}, {3, 5}, {2, 3}, {1, 5}};

        System.out.println(findStartingPointSingleLoop(petrolPumps));

        System.out.println(mostNaiveRecursiveImplementation(petrolPumps, 0, petrolPumps.length - 1));

        System.out.println(dynamicProgrammingSortOf(petrolPumps));

        System.out.println(queueSolution(petrolPumps));
        System.out.println(queueSolutionGFG(petrolPumps));
}

    private static boolean queueSolutionGFG(int[][] petrolPumps) {
        int start = 0, end = 1, petrol = 0;
        Queue<Integer> petrolPumpQueue = new LinkedList<>();

        for (int i = 0; i < petrolPumps.length; i++)
            petrolPumpQueue.add(i);
    }

    private static int queueSolution(int[][] petrolPumps) {
        int start = 0, end = petrolPumps.length - 1, petrol = 0;
        Queue<Integer> petrolPumpQueue = new LinkedList<>();

        for (int i = 0; i < petrolPumps.length; i++)
            petrolPumpQueue.add(i);

        while (start != end) {
            Integer pump = petrolPumpQueue.peek();
            petrol += petrolPumps[pump][0] - petrolPumps[pump][1];
            if (petrol < 0) {
                if (pump == petrolPumps.length - 1)
                    return -1;

                petrol = 0;
                petrolPumpQueue.poll();
                petrolPumpQueue.add(pump);
                start++;
                end = (start + (petrolPumps.length - 1));
            }
            else start++;
        }

        return petrolPumpQueue.poll();
    }

    private static int dynamicProgrammingSortOf(int[][] petrolPumps) {
        int[] prefixes = new int[petrolPumps.length];
        prefixes[0] = petrolPumps[0][0] - petrolPumps[0][1];
        for (int i = 1; i < petrolPumps.length; i++) {
            prefixes[i] = prefixes[i - 1] + petrolPumps[i][0] - petrolPumps[i][1];
        }

        int[] suffixes = new int[petrolPumps.length];
        int end = petrolPumps.length - 1;
        suffixes[end] = petrolPumps[end][0] - petrolPumps[end][1];
        for (int i = end - 1; i > -1; i--) {
            suffixes[i] = suffixes[i + 1] + petrolPumps[i][0] - petrolPumps[i][1];
        }

        for (int i = 0; i < petrolPumps.length; i++) {
            if (i == end && prefixes[i] >= 0)
                return i;
            if (prefixes[i] >= 0 && suffixes[i + 1] >= 0)
                return i;
        }

        return -1;
    }

    /*
    Bringing start closer and closer to end, don't increment "end" like in the Queue solution from GFG.
    This implementation, in the worst case, is O(n*n). This is WHY it's better to use a Queue.
     */
    private static int mostNaiveRecursiveImplementation(int[][] petrolPumps, int start, int end) {
        if (start == petrolPumps.length)
            return -1;

        int totalPetrol = 0;
        int head = start;
        while (start < end) {
            // start may become bigger than the array size HERE, if we start at arrayLength - 1 for example
            totalPetrol += petrolPumps[start % petrolPumps.length][0] - petrolPumps[start % petrolPumps.length][1];
            if (totalPetrol < 0) {
                return mostNaiveRecursiveImplementation(petrolPumps, start + 1, start + petrolPumps.length - 1);
            }
            else start++;
        }
        return head;
    }

    private static int findStartingPointSingleLoop(int[][] petrolPumps) {
        int totalPetrol = 0, startingPetrol = 0;
        int head = petrolPumps.length;
        for (int i = 0; i < petrolPumps.length; i++) {
            totalPetrol += petrolPumps[i][0] - petrolPumps[i][1];

            if ((startingPetrol += petrolPumps[i][0] - petrolPumps[i][1]) < 0) {
                startingPetrol = 0;
                head = i + 1;
            }
        }

        return (totalPetrol < 0) ? -1 : head;
    }
}
