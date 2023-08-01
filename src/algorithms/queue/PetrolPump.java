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

    private static int queueSolutionGFG(int[][] petrolPumps) {
        int start = 0, end = petrolPumps.length - 1, petrol = 0;
        Queue<Integer> petrolPumpQueue = new LinkedList<>();

        for (int i = 0; i < petrolPumps.length; i++)
            petrolPumpQueue.add(i);

        while (start != end) {
            Integer pump = petrolPumpQueue.peek();
            petrol += petrolPumps[pump][0] - petrolPumps[pump][1];
//            if (petrol < 0) {
//                while (petrol < 0) {
//                    if (pump == petrolPumps.length)
//                        return -1;
//                    petrol = petrol - petrolPumps[pump][0] + petrolPumps[pump][1];
//                    petrolPumpQueue.poll();
//                    petrolPumpQueue.add(pump);
//                    pump++;
//                }
//                start = pump;
//                end = (start + petrolPumps.length - 1) % petrolPumps.length; // this approach forces you to update the end
//            }
//            else start = (start + 1) % petrolPumps.length;

            if (petrol < 0) {
                while (petrol < 0 && pump != end) { // we only computed until the "end" variable
                    if (pump == petrolPumps.length)
                        return -1;
                    petrol = petrol - petrolPumps[pump][0] + petrolPumps[pump][1];
                    petrolPumpQueue.poll();
                    petrolPumpQueue.add(pump);
                    pump++;
                }
                start = pump;
            }
            // we need modulus because the "end" value will need to go back to the beginning of the array
            else end = (end + 1) % petrolPumps.length;
        }

        return petrolPumpQueue.peek();
    }


    // "start" and the polling of the pumps are NOT RELATED. Start is used here just as a tracker, to make sure we always go all the length till the end, nothing else.
    // but this is NOT efficient. what if you have 100 elements. you should not start again from the beginning of the queue...
    // instead, you subtract the first petrol pump, and do a small computation and see if the new starting point is good.
    // end should go aaaaall the way unti it meets "start" again

    private static int queueSolution(int[][] petrolPumps) {
        int start = 0, end = petrolPumps.length - 1, petrol = 0;
        Queue<Integer> petrolPumpQueue = new LinkedList<>();

        for (int i = 0; i < petrolPumps.length; i++)
            petrolPumpQueue.add(i);

        while (start <= end) {
            Integer pump = petrolPumpQueue.peek();
            petrol += petrolPumps[pump][0] - petrolPumps[pump][1];
            if (petrol < 0) {
                if (pump == petrolPumps.length - 1)
                    return -1;

                petrol = 0;
                start = 0;
                petrolPumpQueue.poll();
                petrolPumpQueue.add(pump);
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

        // not correct

//        for (int i = 0; i < petrolPumps.length; i++) {
//            if (i == end && prefixes[i] >= 0)
//                return i;
//            if (prefixes[i] >= 0 && suffixes[i + 1] >= 0)
//                return i;
//        }

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

    // why does this work... we are not just incrementing the head with 1, we are JUMPING over a lot of possible heads.
    // i think we should do here exactly as in the queue solution AND dynamic-programming solution combined.
    // Meaning, increment start one by one, then compute this "sumFromStart" subtracting it from "currentPetrol"
    // until we get positive "curentPetrol". Later edit: we don't need to do this, we can skip many intermediary starting points
    private static int findStartingPointSingleLoop(int[][] petrolPumps) {
        int totalPetrol = 0, startingPetrol = 0;
        int head = petrolPumps.length;
        int deficit = 0;
        for (int i = 0; i < petrolPumps.length; i++) {
            totalPetrol += petrolPumps[i][0] - petrolPumps[i][1];

            if ((startingPetrol += petrolPumps[i][0] - petrolPumps[i][1]) < 0) {
                deficit = startingPetrol;
                startingPetrol = 0;
                head = i + 1; // try the head after the current one, likely SKIPPING MANY INTERMEDIARY heads
            }
        }

        return (deficit + startingPetrol < 0) ? -1 : head;
    }
}
