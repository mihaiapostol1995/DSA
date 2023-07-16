package algorithms.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TrieNode {
    private static final int ALPHABET_SIZE = 26;
    TrieNode[] children;
    boolean isEndOfWord;

    TrieNode() {
        children = new TrieNode[ALPHABET_SIZE];
        isEndOfWord = false;
    }
}

public class WorldBoggleTrieChatGPT {
    private final TrieNode root;

    public WorldBoggleTrieChatGPT(String[] dictionary) {
        root = new TrieNode();
        for (String word : dictionary) {
            insertWord(word);
        }
    }

    private void insertWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'A';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    public List<String> findWords(char[][] board) {
        int rows = board.length;
        int columns = board[0].length;
        Set<String> result = new HashSet<>();

        boolean[][] visited = new boolean[rows][columns];
        TrieNode current = root;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                char c = board[i][j];
                int index = c - 'A';
                if (current.children[index] != null) {
                    dfs(board, i, j, current.children[index], visited, new StringBuilder(), result);
                }
            }
        }

        return new ArrayList<>(result);
    }

    private void dfs(char[][] board, int row, int col, TrieNode node, boolean[][] visited, StringBuilder sb, Set<String> result) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col]) {
            return;
        }

        char c = board[row][col];
        int index = c - 'A';

        if (node.children[index] == null) {
            return;
        }

        sb.append(c);
        visited[row][col] = true;

        if (node.children[index].isEndOfWord) {
            result.add(sb.toString());
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length && !visited[newRow][newCol]) {
                dfs(board, newRow, newCol, node.children[index], visited, sb, result);
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        visited[row][col] = false;
    }

    public static void main(String[] args) {
        String[] dictionary = {"OATH", "PEA", "EAT", "RAIN"};
        char[][] board = {
                {'O', 'A', 'E', 'N'},
                {'T', 'H', 'G', 'P'},
                {'R', 'A', 'I', 'M'},
                {'E', 'T', 'A', 'P'}
        };

        WorldBoggleTrieChatGPT solver = new WorldBoggleTrieChatGPT(dictionary);
        List<String> words = solver.findWords(board);

        System.out.println("Words found in the Boggle board:");
        for (String word : words) {
            System.out.println(word);
        }
    }
}
