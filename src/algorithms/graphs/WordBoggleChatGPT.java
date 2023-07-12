package algorithms.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
If you'd like to implement the Boggle algorithm without using a Trie data structure and relying solely on DFS, you can modify the code accordingly.
Here's an example implementation using only DFS:
 */
public class WordBoggleChatGPT {
    private static final int[] ROW_NEIGHBORS = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] COL_NEIGHBORS = {-1, 0, 1, -1, 1, -1, 0, 1};

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (String word : words) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (dfsMihai(board, visited, i, j, word, 0)) {
                        result.add(word);
                    }
                }
            }
        }

        return result;
    }

    private boolean dfsMihai(char[][] board, boolean[][] visited, int i, int j, String word, int i1) {
        if (word.length() == i1)
            return true;

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length ||
            word.charAt(i1) != board[i][j])
            return false;

        visited[i][j] = true;

        for (int k = 0; k < 8; k++) {
            if (dfsMihai(board, visited, i +ROW_NEIGHBORS[k], j +COL_NEIGHBORS[k], word, i1 + 1))
                return true;
        }

        visited[i][j] = false;

        return false;
    }

    private boolean dfs(char[][] board, boolean[][] visited, int row, int col, String word, int index) {
        if (index == word.length()) {
            return true; // Found the complete word
        }

        int rows = board.length;
        int cols = board[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col] || board[row][col] != word.charAt(index)) {
            return false; // Current cell is out-of-bounds, already visited, or doesn't match the character at the given index
        }

        visited[row][col] = true; // Mark current cell as visited

        for (int i = 0; i < 8; i++) {
            int newRow = row + ROW_NEIGHBORS[i];
            int newCol = col + COL_NEIGHBORS[i];

            if (dfs(board, visited, newRow, newCol, word, index + 1)) {
                return true; // Continue DFS traversal with the next character of the word
            }
        }

        visited[row][col] = false; // Unmark current cell as visited since we couldn't find a valid word

        return false;
    }
}
