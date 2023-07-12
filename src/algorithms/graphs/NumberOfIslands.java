package algorithms.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

    static int countOfIslands = 0;
    public static void main(String[] args) {
        int[][] mat = {
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {1, 0, 1, 0, 0}};

        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'0', '1', '0', '0', '1'},
                {'1', '0', '0', '1', '1'},
                {'0', '0', '0', '0', '0'},
                {'1', '0', '1', '0', '0'}};


        findNumberOfIslands(mat); // using DFS - traverse till the end
        System.out.println(countOfIslands);

        // chat gpt solution
        System.out.println(numIslands(grid));

        // BFS solution, my solution at the suggestion of Chat gpt
        System.out.println(findNumberOfIslandsBFS(mat));
    }

    private static void findNumberOfIslands(int[][] mat) {
        boolean [][] visited = new boolean[mat.length][mat.length];
        // a more elegant solution (maybe) is to SET the value in the table to 0,
        // instead of using visited array

        for (int rows = 0; rows < mat.length; rows ++) {
            for (int columns = 0; columns < mat.length; columns++) {
                if (visited[rows][columns])
                    continue; // don't count an island twice

                if (mat[rows][columns] == 1) {
                    countOfIslands ++;
                    recursiveSearch(mat, visited, rows, columns);
                }
            }
        }
    }

    private static void recursiveSearch(int[][] mat, boolean[][] visited, int rows, int columns) {
        if (rows < 0 || columns < 0
                || rows >= mat.length || columns >= mat.length
                || visited[rows][columns]
                || mat[rows][columns] == 0) // don't recurse until the end of the table, avoid stack overflow error...
            return;

        if (mat[rows][columns] == 1)
            visited[rows][columns] = true; // keep going, move to the recursive calls

        recursiveSearch(mat, visited, rows + 1, columns);
        recursiveSearch(mat, visited, rows - 1, columns);
        recursiveSearch(mat, visited, rows, columns + 1);
        recursiveSearch(mat, visited, rows, columns - 1);

        // now diagonals
        recursiveSearch(mat, visited, rows + 1, columns + 1);
        recursiveSearch(mat, visited, rows - 1, columns - 1);
        recursiveSearch(mat, visited, rows + 1, columns - 1);
        recursiveSearch(mat, visited, rows - 1, columns + 1);
    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    dfs(grid, i, j);
                }
            }
        }

        return numIslands;
    }

    private static void dfs(char[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Base cases for out-of-bounds or already visited cells
        if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] == '0') {
            return;
        }

        // Mark the current cell as visited
        grid[row][col] = '0';

        // Perform DFS on adjacent and diagonal cells
        dfs(grid, row - 1, col);     // Up
        dfs(grid, row + 1, col);     // Down
        dfs(grid, row, col - 1);     // Left
        dfs(grid, row, col + 1);     // Right
        dfs(grid, row - 1, col - 1); // Top-left
        dfs(grid, row - 1, col + 1); // Top-right
        dfs(grid, row + 1, col - 1); // Bottom-left
        dfs(grid, row + 1, col + 1); // Bottom-right
    }


    /* chat gpt came with the idea
     he used these two additional paramteres:

     boolean[][] visited = new boolean[rows][cols];
     int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

     and this condition:

                     if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
                    && grid[newRow][newCol] == '1' && !visited[newRow][newCol]) {
                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }

     */

    private static int findNumberOfIslandsBFS(int[][] mat) {
        boolean [][] visited = new boolean[mat.length][mat.length];

        int countOfIslands = 0;
        for (int rows = 0; rows < mat.length; rows ++) {
            for (int columns = 0; columns < mat.length; columns++) {
                if (visited[rows][columns])
                    continue; // don't count an island twice

                if (mat[rows][columns] == 1) {
                    countOfIslands ++;

                    bfs(mat, visited, rows, columns);
                }
            }
        }
        return countOfIslands;
    }

    // purpose of this function is to visit all adjacent island-parts (marked as 1 in the grid)
    private static void bfs(int[][] mat, boolean[][] visited, int rows, int columns) {
        Queue<Island> queueOfIslands = new LinkedList<>();
        queueOfIslands.add(new Island(rows, columns));

        while (!queueOfIslands.isEmpty()) {
            Island island = queueOfIslands.poll();

            int islandRow = island.row;
            int islandColumn = island.column;

            visited[islandRow][islandColumn] = true;

            if (islandRow + 1 < mat.length && mat[islandRow + 1][islandColumn] == 1
                && !visited[islandRow + 1][islandColumn]) {
                queueOfIslands.add(new Island(islandRow + 1, islandColumn));
            }
            if (islandRow + 1 < mat.length && islandColumn + 1 < mat.length
                && mat[islandRow + 1][islandColumn + 1] == 1
                && !visited[islandRow + 1][islandColumn + 1]) {
                queueOfIslands.add(new Island(islandRow + 1, islandColumn + 1));
            }
            if (islandColumn + 1 < mat.length
                    && mat[islandRow][islandColumn + 1] == 1
                    && !visited[islandRow][islandColumn + 1]) {
                queueOfIslands.add(new Island(islandRow, islandColumn + 1));
            }
            if (islandRow - 1 >= 0 && mat[islandRow - 1][islandColumn] == 1
                && !visited[islandRow -1][islandColumn]) {
                queueOfIslands.add(new Island(islandRow - 1, islandColumn));
            }
            if (islandColumn - 1 >= 0
                    && mat[islandRow][islandColumn - 1] == 1
                    && !visited[islandRow][islandColumn - 1]) {
                queueOfIslands.add(new Island(islandRow, islandColumn -1));
            }
            if (islandRow - 1 >= 0 && islandColumn - 1 >= 0
                    && mat[islandRow - 1][islandColumn -1] == 1
                    && !visited[islandRow - 1][islandColumn - 1]) {
                queueOfIslands.add(new Island(islandRow - 1, islandColumn -1));
            }
            if (islandColumn + 1 < mat.length && islandRow - 1 >= 0
                    && mat[islandRow - 1][islandColumn + 1] == 1
                    && !visited[islandRow - 1][islandColumn + 1]) {
                queueOfIslands.add(new Island(islandRow - 1, islandColumn + 1));
            }
            if (islandRow + 1 < mat.length && islandColumn - 1 >= 0
                    && mat[islandRow + 1][islandColumn -1] == 1
                    && !visited[islandRow + 1][islandColumn - 1]) {
                queueOfIslands.add(new Island(islandRow + 1, islandColumn -1));
            }
        }
    }

    static class Island {
        int row;
        int column;

        public Island(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
