import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Interview {

    //1.check if two strings are anagrams
    public static void main(String[] args) {
        HashMap<String, Integer> anagramFirstString = new HashMap<>();
        HashMap<String, Integer> anagramSecondString = new HashMap<>();

        String firstString = "Eleven plus two";
        String secondString = "Twelve plus two";

        //O(2n)
        populateHashMap(anagramFirstString, firstString, firstString.toCharArray());
        populateHashMap(anagramSecondString, secondString, secondString.toCharArray());

        System.out.println(checkForAnagrams(anagramFirstString, anagramSecondString));

        new PrivateMethod().callMe();
    }

    private static boolean checkForAnagrams(HashMap<String, Integer> anagramFirstString, HashMap<String, Integer> anagramSecondString) {
        for (String firstStringUsed : anagramFirstString.keySet()) {
            if (anagramSecondString.containsKey(firstStringUsed)) {
                int i1 = anagramSecondString.get(firstStringUsed);
                int i2 = anagramFirstString.get(firstStringUsed);
                if (i1 != i2) {
                    System.out.println("the number of occurences does not match: "
                            + i1
                            +" and " + i2 + " for: " + firstStringUsed);
                    return false;
                }
            }
            else {
                System.out.println("not an anagram");
                return false;
            }
        }
        return true;
    }

    private static void populateHashMap(HashMap<String, Integer> anagramFirstString, String firstString, char[] firstStringChars) {
        for (int i = 0; i < firstStringChars.length; i++) {
            String stringFromChar = String.valueOf(firstString.charAt(i));

            System.out.println("adding " + stringFromChar);

            if (stringFromChar.equals(" ")) continue;

            stringFromChar = stringFromChar.toLowerCase();
            if (anagramFirstString.containsKey(stringFromChar)) {
                anagramFirstString.put(stringFromChar, anagramFirstString.get(stringFromChar) +1);
            }
            else anagramFirstString.put(stringFromChar, 1);
        }
    }
}
