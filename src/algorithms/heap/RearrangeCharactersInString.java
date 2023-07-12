package algorithms.heap;

import java.util.*;

public class RearrangeCharactersInString {
    public static void main(String[] args) {
        char[] letters = new char[]{ 'b', 'b' ,'c', 'c', 'c', 'c', 'd', 'a', 'c'};

        System.out.println(rearrangeCharacters(letters));
        System.out.println(rearrangeCharactersAndCreateString(letters));
    }

    private static boolean rearrangeCharacters(char[] letters) {
        HashMap<Character, Integer> letterOccurrences = new HashMap<>();

        for (char letter : letters) {
            letterOccurrences.merge(letter, 1, Integer::sum);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (Character c : letterOccurrences.keySet()) {
            queue.add(letterOccurrences.get(c));
        }

        for (int i = 0; i < letters.length || !queue.isEmpty(); i ++) {
            int occurrences = queue.poll();
            if (queue.isEmpty())
                return occurrences == 1;

            int secondMostOccurrences = queue.poll();
            int difference = occurrences - secondMostOccurrences;

            if (difference == 0 || difference == 1) {
                if (queue.isEmpty()) return true;
            }
            else {
                queue.add(difference);
            }
        }

        return queue.isEmpty();
    }

    private static String rearrangeCharactersAndCreateString(char[] letters) {
        HashMap<Character, Integer> letterOccurrences = new HashMap<>();

        for (char letter : letters) {
            letterOccurrences.merge(letter, 1, Integer::sum);
        }

        PriorityQueue<Character> queue = new PriorityQueue<>(
                Comparator.comparingInt(letterOccurrences::get).reversed() // Important to keep the
                // reversed order of character frequency
        );
        queue.addAll(letterOccurrences.keySet());

        StringBuilder rearranged = new StringBuilder();

        Character previous = null;

        while (!queue.isEmpty()) {
            Character letter = queue.poll();
            int occurrences = letterOccurrences.get(letter);
            if (occurrences > 0) {
                letterOccurrences.put(letter, letterOccurrences.get(letter) - 1);
            }

            if (queue.isEmpty() && occurrences > 1)
                return "Not possible";
            else {
                rearranged.append(letter);

                if (previous != null && letterOccurrences.get(previous) > 0)
                    queue.add(previous);

                previous = letter;
            }
        }

        return rearranged.toString();
    }
}
