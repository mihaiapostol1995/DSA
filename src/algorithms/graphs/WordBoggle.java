package algorithms.graphs;

import java.util.Arrays;
import java.util.HashSet;

public class WordBoggle {

    static HashSet<String> hashDictionary = new HashSet<>();
    public static void main(String[] args) {
        String[] dictionary = {"GEEKS", "FOR", "QUIZ", "GO"};
        char[][] boggle = {
            {'G', 'I', 'Z'},
            {'U', 'E', 'K'},
            {'Q', 'S', 'E'}};
        hashDictionary = new HashSet<>(Arrays.asList(dictionary));


        isWord(boggle);

        // more efficient, taking only the word
        System.out.println(new WordBoggleChatGPT().findWords(boggle, dictionary));
    }

    private static void isWord(char[][] boggle) {
        boolean[][] visited = new boolean[boggle.length][boggle[0].length];

        for (int rows = 0; rows < boggle.length; rows++) {
            for (int columns = 0; columns < boggle[0].length; columns++) {
                dfs(String.valueOf(boggle[rows][columns]), boggle, visited, 0, 0);
            }
        }
    }

    private static void dfs(String word,
                            char[][] boggle, boolean[][] visited,
                            int row, int column) {
        if (visited[row][column])
            return;

        if (hashDictionary.contains(word))
            System.out.println(word);
        // keep searching

        visited[row][column] = true;

        // You can also concatenate strings on the next iteration, not inline it here.
//        if (row + 1 < boggle.length) dfs(word + boggle[row+1][column], boggle, visited, row+1, column);
//        if (column + 1 < boggle[0].length) dfs(word + boggle[row][column+1], boggle, visited, row, column+1);
//        if (row > 0) dfs(word + boggle[row-1][column], boggle, visited, row-1, column);
//        if (column > 0) dfs(word + boggle[row][column-1], boggle, visited, row, column-1);
//
//        if (row + 1 < boggle.length && column + 1 < boggle[0].length) dfs(word + boggle[row+1][column+1], boggle, visited, row+1, column+1);
//        if (column > 0 && row > 0) dfs(word + boggle[row-1][column-1], boggle, visited, row-1, column-1);
//        if (row + 1 < boggle.length && column > 0) dfs(word + boggle[row+1][column-1], boggle, visited, row+1, column-1);
//        if (row > 0 && column + 1 < boggle[0].length) dfs(word + boggle[row-1][column+1], boggle, visited, row-1, column+1);

        // GFG solution is more elegant: use for loops, ONLY FOR ADJACENT cells
        for (int i = row - 1; i < boggle.length && i <= row + 1; i++) {
            for (int j = column - 1; j < boggle[0].length && j <= column + 1; j++) {
                if (i >= 0 && j >= 0)
                    dfs(word + boggle[i][j], boggle, visited, i, j);
            }
        }

        visited[row][column] = false;
    }
}
