package algorithms.strings;

import java.util.HashMap;

public class RabinKarpAlgorithm {

    public static void main(String[] args) {
        String text = "THIS IS A TEST TEXT", pattern = "TEXT";

        findPattern(text, pattern);

        // sliding window, i think
        findPatternSlidingWindow(text, pattern);
    }

    private static void findPatternSlidingWindow(String text, String pattern) {
        char[] charText = text.toCharArray();
        for (int i = pattern.length(); i <= charText.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i - pattern.length(); j < i; j++) {
                sb.append(charText[j]);
            }
            if (sb.toString().equals(pattern))
                System.out.println("found the string");
        }
    }

    private static void findPattern(String text, String pattern) {
        HashMap<Integer, String> map = new HashMap<>();

        char[] charText = text.toCharArray();
        for (int i = 0; i < charText.length; i++) {

            int j = 0; //this is sliding the window
            while (j <= i && j < pattern.length()) {
                int modulus = (j % (pattern.length()));

                if (map.get(modulus) != null)
                    map.put(modulus, map.get(modulus) + charText[i]);
                else map.put(modulus, String.valueOf(charText[i]));

                if (map.get(modulus) != null) {
                    if (map.get(modulus).length() == (pattern.length()))
                        if (map.get(modulus).equals(pattern))
                            System.out.println("found it");
                        else map.remove(modulus);
                }
                j++;
            }
        }
    }
}
