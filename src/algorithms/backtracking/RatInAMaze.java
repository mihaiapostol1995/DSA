package algorithms.backtracking;

import java.util.Arrays;

public class RatInAMaze {
    public static void main(String[] args) {
        int [][] maze = {
        {1, 0, 0, 0},
        {1, 1, 0, 1},
        {0, 1, 0, 0},
        {1, 1, 1, 1}};

        int i = 0, j = 0;

        int length = maze.length;
        int[][] solution = new int[length][length];
        solution[length-1][length-1] = 1;

        findWayOut(maze, solution, i, j);
        System.out.println(Arrays.deepToString(solution));
    }

    private static boolean findWayOut(int[][] maze, int[][] solution, int i, int j) {
        if (i == maze.length - 1 && j == maze.length - 1) {
            System.out.println("the end");
            return true;
        }

        if (i == maze.length || j == maze.length || maze[i][j] == 0) {
            return false;
        }

        solution[i][j] = 1;

        // Move down
        if (findWayOut(maze, solution, i + 1, j)) {
            return true;
        }
        // Move right
        else if (findWayOut(maze, solution, i, j + 1)) {
            return true;
        }

        solution[i][j] = 0;

        return false;
    }
}
