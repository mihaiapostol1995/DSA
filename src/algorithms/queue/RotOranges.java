package algorithms.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RotOranges {
    public static void main(String[] args) {
        int[][] oranges = {
                {2, 1, 0, 2, 1},
                {1, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}
        };

        int[][] oranges2 = {
                {2, 1, 0, 2, 1},
                {1, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}
        };

        System.out.println(rotOrangesQueue(oranges));

        System.out.println(stepByStep(oranges2));
    }

    private static int stepByStep(int[][] oranges) {
        Queue<Array> rottenOranges = new LinkedList<>();

        int times = 0;
        while (true) {
            boolean nothingChanged = true;
            for (int i = 0; i < oranges.length; i++) {  // i's are rows
                for (int j = 0; j < oranges[i].length; j++) { // j's are columns {
                    if (oranges[i][j] == 2) {
                        Array element = new Array(i, j);
                        if (isSafe(oranges, element.row, element.column + 1) && oranges[element.row][element.column + 1] == 1) {
                            rottenOranges.add(new Array(element.row, element.column + 1));
                            nothingChanged = false;
                        }
                        if (isSafe(oranges, element.row + 1, element.column) && oranges[element.row + 1][element.column] == 1) {
                            rottenOranges.add(new Array(element.row + 1, element.column));
                            nothingChanged = false;
                        }
                        if (isSafe(oranges, element.row - 1, element.column) && oranges[element.row - 1][element.column] == 1) {
                            rottenOranges.add(new Array(element.row - 1, element.column));
                            nothingChanged = false;
                        }
                        if (isSafe(oranges, element.row, element.column - 1) && oranges[element.row][element.column - 1] == 1) {
                            rottenOranges.add(new Array(element.row, element.column - 1));
                            nothingChanged = false;
                        }
                    }
                }
            }
            while (!rottenOranges.isEmpty()) {
                Array element = rottenOranges.poll();
                oranges[element.row][element.column] = 2; // rot this orange
            }
            if (nothingChanged)
                break;
            else {
                times++;
            }
        }
        return times;
    }

    private static int rotOrangesQueue(int[][] oranges) {
        Queue<Array> orangesRotten = new LinkedList<>();

        int times = -1;
        int count = 0;
        for (int i = 0; i < oranges.length; i++)  // i's are rows
            for (int j = 0; j < oranges[i].length; j++)  // j's are columns
                if (oranges[i][j] == 2) {
                    orangesRotten.add(new Array(i , j));
                    count++;
                }

        // instead of using the size as the count of elements, GFG's solution was to use a "delimiter", with coordinates (-1, -1)
        // not sure if this is BFS, DGS or both haha
        while (!orangesRotten.isEmpty()) {
            Array element = orangesRotten.poll();
            count--;
            if (count == 0) {
                times++;
                count = orangesRotten.size();
            }
            oranges[element.row][element.column] = 2; // rot it here. more elegant than to do it inside ALL the if's below (no impact, right?)

            if (isSafe(oranges, element.row, element.column + 1) && oranges[element.row][element.column + 1] == 1) {
                orangesRotten.add(new Array(element.row, element.column + 1));
            }
            if (isSafe(oranges, element.row + 1, element.column) && oranges[element.row + 1][element.column] == 1) {
                orangesRotten.add(new Array(element.row + 1, element.column));
            }
            if (isSafe(oranges, element.row - 1, element.column) && oranges[element.row - 1][element.column] == 1) {
                orangesRotten.add(new Array(element.row - 1, element.column));
            }
            if (isSafe(oranges, element.row, element.column - 1) && oranges[element.row][element.column - 1] == 1) {
                orangesRotten.add(new Array(element.row, element.column - 1));
            }
        }
        return times;
    }

    private static boolean isSafe(int[][] oranges, int i, int i1) {
        return i >= 0 && i1 >= 0 && i < oranges.length && i1 < oranges[i].length;
    }

    static class Array {
        int row;
        int column;
        Array(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
