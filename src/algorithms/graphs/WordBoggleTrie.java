package algorithms.graphs;

public class WordBoggleTrie {
    public static void main(String[] args) {
        String[] dictionary = {"GEEKS", "FOR", "QUIZ", "GO"};
        char[][] boggle = {
                {'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'}};

        TrieNode root = new TrieNode();
        for (String word : dictionary)
            root.insert(word);

        findInTrie(boggle, root);

        root.search("GEEKS");
    }

    private static void findInTrie(char[][] boggle, TrieNode root) {
        boolean [][] visited = new boolean[boggle.length][boggle[0].length];

        for (int i = 0; i < boggle.length; i++) {
            for (int j = 0; j < boggle[0].length; j++) {
                TrieNode node = root.children[boggle[i][j] - 'A'];
                if (node != null) {
                    searchWordsDFS(String.valueOf(boggle[i][j]), node, i, j, boggle, visited);
                }
            }
        }
    }

    private static void searchWordsDFS(String boggleChunk, TrieNode root, int row, int column, char[][]boggle, boolean[][] visited) {
        if (root.endOfWord)
            System.out.println("found word: " + boggleChunk);

        visited[row][column] = true;

        for (int i = row - 1; i < boggle.length && i <= row + 1; i++) {
            for (int j = column - 1; j < boggle[0].length && j <= column + 1; j++) {
                if (i >= 0 && j >= 0 // this is interesting...
                        && !visited[i][j]) {
                    TrieNode current = root.children[boggle[i][j] - 'A']; // instead of searching the WHOLE word
                    if (current != null) {
                        String newString = boggleChunk + boggle[i][j];
                        searchWordsDFS(newString, current, i, j, boggle, visited);
                    }
                }
            }
        }

        visited[row][column] = false;
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean endOfWord = false;

        public void insert(String word) {
            TrieNode current = this;

            for (int i = 0; i < word.length(); i++) {
                char index = word.charAt(i);
                if (current.children[index - 'A'] == null)
                    current.children[index - 'A'] = new TrieNode(); // VERY IMPORTANT!
                current = current.children[index - 'A'];
            }

            current.endOfWord = true;
        }

        public TrieNode search(String word) {
            TrieNode current = this;
            for (int i = 0; i < word.length(); i++) {
                char index = word.charAt(i);
                current = current.children[index - 'A'];

                if (current == null) return null;
            }
            return current;
        }
    }
}
