package algorithms.dynamicprogramming;

public class EditDistance {
    public static void main(String[] args) {
        String string1 = "sunday";
        String string2 = "saturday";
        int [][] table = new int[string1.length() + 1][string2.length() + 1];

        editDistance(string1, string2, table);
    }

    private static void editDistance(String string1, String string2, int [][] table) {
        char[] char1 = string1.toCharArray();
        char[] char2 = string2.toCharArray();

        for (int column = 1; column < char1.length + 1; column ++)
            table[column][0] = column;

        for (int row = 1; row < char2.length + 1; row ++)
            table[0][row] = row;

        for (int column = 1; column < char1.length + 1; column ++) {
            for (int row = 1; row < char2.length + 1; row ++) {

                // Recur
                int min = Math.min(
                        Math.min(
                                table[column - 1][row - 1],
                                table[column - 1][row]),
                        table[column][row - 1]
                );

                if (string1.charAt(column - 1) == string2.charAt(row - 1))
                    table[column][row] = min;

                // We must recur, so this operation costs us "1"
                else table[column][row] = 1 + min;
            }
        }

        System.out.println(table[string1.length()][string2.length()]);
    }
}
