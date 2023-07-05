package algorithms.dynamicprogramming;

import java.util.Arrays;

public class JobScheduling {
    public static void main(String[] args) {
        Job [] jobArray = new Job[7];

        jobArray[0] = new Job(1, 2, 20);
        jobArray[1] = new Job(3, 10, 20);
        jobArray[2] = new Job(4, 11, 10);
        jobArray[3] = new Job(6, 19, 65);
        jobArray[4] = new Job(20, 22, 20);
        jobArray[5] = new Job(4, 23, 30);
        jobArray[6] = new Job(2, 100, 50);

        System.out.println(maxProfitSimpler(jobArray, jobArray.length - 1));
        maxProfitSimplerDynamic(jobArray, jobArray.length - 1);
    }

    private static int maxProfit(Job[] jobArray, int index) {
        int profit = 0;
        int calculatedIndex = 0;

        if (index == jobArray.length - 1) {
            profit = jobArray[jobArray.length - 1].profit;
            calculatedIndex = calculateIndex(index - 1, jobArray);
        }
        // base case
        else if (index == 0) {
            if (jobArray[0].endRange <= jobArray[1].startRange) return jobArray[0].profit;
            else return 0;
        }
        else if (jobArray[index].endRange <= jobArray[index + 1].startRange) {
            profit = jobArray[index].profit;
            calculatedIndex = calculateIndex(index - 1, jobArray);
        }

        return Math.max(
                maxProfit(jobArray, index - 1),
                profit + maxProfit(jobArray, calculatedIndex)); //we can optimize this search, using binary search
    }

    private static int calculateIndex(int index, Job[] jobArray) {
        int low = 0;
        int high = index;
        int split = low + (index - low) / 2;

        int foundIndex = -1;

        while (low <= high) {
            if (jobArray[split].endRange <= jobArray[index].startRange) {
                foundIndex = split;
                low = split + 1;
            }
            else high = split - 1;
            split = low + (high - low) / 2;
        }

        return foundIndex;
    }

    private static int calculateIndexLinear(int index, Job[] jobs, Job currentIndex) {
        int key = currentIndex.startRange;

        for (int i = index - 1; i >= 0; i--) {
            if (jobs[i].endRange <= key) {
                return index;
            }
        }
        return 0;
    }

    private static int maxProfitSimpler(Job[] jobArray, int index) {
        //base case
        if (index == 0) return jobArray[0].profit;
        if (index == -1) return 0;

        return Math.max(maxProfitSimpler(jobArray, index - 1),
                jobArray[index].profit + maxProfitSimpler(jobArray, calculateIndex(index, jobArray)));
    }

    private static void maxProfitSimplerDynamic(Job[] jobArray, int index) {
        int [] table = new int[jobArray.length];

        table[0] = jobArray[0].profit;

        for (int i = 1; i < jobArray.length; i++) {
            int calculatedIndex = calculateIndex(i, jobArray);

            if (calculatedIndex == -1) table[i] = Math.max(table[i - 1], jobArray[i].profit);
            else table[i] = Math.max(table[i - 1], jobArray[i].profit + table[calculatedIndex]);
        }

        System.out.println(Arrays.toString(table));
    }

    static class Job {
        int startRange;
        int endRange;
        int profit;

        public Job(int startRange, int endRange, int profit) {
            this.startRange = startRange;
            this.endRange = endRange;
            this.profit = profit;
        }
    }
}
