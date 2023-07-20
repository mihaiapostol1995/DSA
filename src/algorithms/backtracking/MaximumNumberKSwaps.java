package algorithms.backtracking;

public class MaximumNumberKSwaps {

    private static String MAX_NUMBER;
    public static void main(String[] args) {
        // String number = "129814999";
        String number = "999984211";  // what if the correct number is there from THE START?
        int k = 4;

        //System.out.println(swapWrong(number, k, 0));

        MAX_NUMBER = number;
        swapBacktrack(number.toCharArray(), k, 0);
        System.out.println(MAX_NUMBER);
    }

    // This example of 129814999 input shows EXACTLY why you need to do a back-tracking approach. You need to combine with all duplicated max numbers!
    // So this below approach won't work.
    private static int swapWrong(String number, int k, int startingIndex) {
        int intNumber = Integer.parseInt(number);

        char[] chars = number.toCharArray();
        for (int i = startingIndex + 1; i < chars.length; i++) {
            char toReplace = number.charAt(startingIndex);

            StringBuilder builder = new StringBuilder(number);
            builder.replace(startingIndex, startingIndex + 1, String.valueOf(number.charAt(i)));
            builder.replace(i ,i + 1, String.valueOf(toReplace));

            if (Integer.parseInt(builder.toString()) > intNumber)
                intNumber = Integer.parseInt(builder.toString());
        }

        if (Integer.parseInt(number) != intNumber)
            k--;
        if (k > 0 && startingIndex < chars.length - 1)
            return Math.max(intNumber, swapWrong(String.valueOf(intNumber), k, startingIndex + 1));

        return intNumber;
    }

    private static void swapBacktrack(char[] chars, int k, int startingIndex) {
        if (k == 0 || startingIndex >= chars.length)
            return;

        char maxDigitFound = chars[startingIndex];
        for (int i = startingIndex; i < chars.length; i++) {
            if (chars[i] > chars[startingIndex])
                maxDigitFound = chars[i];
        }

        if (maxDigitFound != chars[startingIndex])
            k--;

        for (int i = startingIndex + 1; i < chars.length; i++) {
            int currentChar = chars[i];

            if (currentChar == maxDigitFound) {
                swapCharacters(chars, startingIndex, i);

                String currentNumber = String.valueOf(chars);
                if (currentNumber.compareTo(MAX_NUMBER) > 0) {
                    // lexicographically compare, nice ChatGPT!
                    MAX_NUMBER = currentNumber;
                }

                // startingIndex + 1, to advance the index with the current String composition
                swapBacktrack(chars, k, startingIndex + 1);

                // backtrack
                // swapCharacters(chars, i, startingIndex);
                swapCharacters(chars, startingIndex, i);
            }
        }
    }

    private static void swapCharacters(char[] chars, int startingIndex, int i) {
        char temporary = chars[startingIndex];
        chars[startingIndex] = chars[i];
        chars[i] = temporary;
    }
}
