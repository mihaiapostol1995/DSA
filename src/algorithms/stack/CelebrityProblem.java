package algorithms.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CelebrityProblem {
    public static void main(String[] args) {
//        int[][] matrixOfPeople = {
//                { 0, 0, 1, 0 },
//                { 0, 0, 1, 0 },
//                { 0, 0, 0, 0 },
//                { 0, 0, 1, 0 } };

        int[][] matrixOfPeople = {
                { 0, 1, 1, 0 },
                { 0, 0, 0, 0 },
                { 0, 1, 0, 1 },
                { 0, 1, 1, 0 } };


        findCelebrityNaiveImplementation(matrixOfPeople); // creating a Person is already O(n*n)
        System.out.println(findCelebrityFasterMaybe(matrixOfPeople));

        /*
        This implementation, findCelebrityFasterMaybe and findCelebrityTwoPointerLike are elimination-based.
        Trying to see if i knows j provides you with TWO pieces of information. If i knows j, i cannot be the celebrity. Else, j cannot be.
        You must make use of BOTH these pieces of information!
         */
        // this one below is chat GPT inspired
        System.out.println(findCelebrityStack(matrixOfPeople));

        System.out.println(findCelebrityTwoPointerLike(matrixOfPeople));

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        arrayLists.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0)));
        arrayLists.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0)));
        arrayLists.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        arrayLists.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0)));
        System.out.println(findCelebrityAdjacencyList(arrayLists));
    }

    private static int findCelebrityAdjacencyList(ArrayList<ArrayList<Integer>> matrixOfPeople) {
        ArrayList<ArrayList<Integer>> acquantainces = new ArrayList<>();
        int celebrity = -1;

        for (int i = 0; i < matrixOfPeople.size(); i++) {
            ArrayList<Integer> person = new ArrayList<>();
            for (int j = 0; j < matrixOfPeople.get(i).size(); j++) {
                if (i != j && matrixOfPeople.get(i).get(j) == 1)
                    person.add(j);
            }
            if (person.isEmpty()) // this person doesn't know anyone
                celebrity = i;
            else acquantainces.add(person);
        }

        if (celebrity == -1)
            return -1;

        for (ArrayList<Integer> acquantaince : acquantainces) {
            if (!acquantaince.contains(celebrity))
                return -1;
        }

        return celebrity;
    }

    private static int findCelebrityTwoPointerLike(int[][] matrixOfPeople) {
        int celebrity = 0; // first pointer, that points to 0 person
        for (int i = 1; i < matrixOfPeople.length; i++) {
            if (knows(celebrity, i, matrixOfPeople))
                celebrity = i; // "celebrity" CANNOT be a celebrity, because it knows someone. Eliminate it, and consider i.
            else continue; // Else, i cannot be a celebrity, because celebrity doesn't know i itself, so we just keep iterating.
        }
        // you can think of celebrity as 1st pointer, and i as the 2nd pointer

        // common part with my implementation
        for (int i = 0; i < matrixOfPeople.length; i++) {
            if (i != celebrity && (!knows(i, celebrity, matrixOfPeople)
                    || knows(celebrity, i, matrixOfPeople))) // you can check these IN THE SAME ITERATION!
                return -1;
        }

        return celebrity;
    }

    private static int findCelebrityStack(int[][] matrixOfPeople) {
        Stack<Integer> candidates = new Stack<>();

        for (int i = 0; i < matrixOfPeople.length; i++)
            candidates.push(i);

        while (candidates.size() > 1) {
            int person1 = candidates.pop();
            int person2 = candidates.pop();

            if (knows(person1, person2, matrixOfPeople))
                candidates.push(person2);
            else if (knows(person2, person1, matrixOfPeople))
                candidates.push(person1);
        }

        int celebrity = candidates.pop();


        // common part with my implementation
        for (int i = 0; i < matrixOfPeople.length; i++) {
            if (i != celebrity && (!knows(i, celebrity, matrixOfPeople)
                    || knows(celebrity, i, matrixOfPeople))) // you can check these IN THE SAME ITERATION!
                return -1;
        }

        return celebrity;
    }

    private static boolean knows(int person1, int person2, int[][] matrixOfPeople) {
        return matrixOfPeople[person1][person2] == 1;
    }

    private static int findCelebrityFasterMaybe(int[][] matrixOfPeople) {
        List<Integer> possibleCelebrities = new ArrayList<>();
        // add possible celebrities. We only need to consider one person
        for (int i = 0; i < matrixOfPeople[0].length; i++) {
            if (matrixOfPeople[0][i] == 1) {
                possibleCelebrities.add(i);
            }
        }

        // It is logically possible to have only ONE person as the celebrity (the celebrity must be known by EVERYONE, except himself).
        // But my implementation here doesn't use this insight.
        // So in the worst case, this implementation is also O(n*n), if the 1st person from above code knows everyone.
        for (Integer celebrity : possibleCelebrities) {
            int result = findCelebrity(matrixOfPeople, celebrity);
            if (result != -1)
                return result;
        }
        return -1;
    }

    // This approach is definitely NOT OPTIMISING for the least number of questions
    private static int findCelebrity(int[][] matrixOfPeople, int celebrity) {
        // all the people know this person
        for (int i = 0; i < matrixOfPeople.length; i++) {
            if (i != celebrity && (matrixOfPeople[i][celebrity] != 1
                    || matrixOfPeople[celebrity][i] != 0))
                return -1;
        }
        return celebrity;
    }

    private static void findCelebrityNaiveImplementation(int[][] matrixOfPeople) {
        List<Person> people = convertToPeople(matrixOfPeople);

        Stack<Person> stackOfPeople = new Stack<>();
        final Person[] celebrity = new Person[1];
        people.forEach(person -> {
            if (!person.acquaintances.isEmpty()) {
                stackOfPeople.add(person);
            }
            else celebrity[0] = person;
        });

        System.out.println(findCelebrity(stackOfPeople, celebrity[0]));
    }

    private static int findCelebrity(Stack<Person> stackOfPeople, Person celebrity) {
        if (celebrity == null)
            return -1;

        while (!stackOfPeople.isEmpty()) {
            Person poppedPerson = stackOfPeople.pop();
            boolean found = false;
            for (Integer a : poppedPerson.acquaintances) {
                if (a == celebrity.id) {
                    found = true;
                    break;
                }
            }
            if (!found)
                return -1;
        }
        return celebrity.id;
    }

    private static List<Person> convertToPeople(int[][] matrixOfPeople) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < matrixOfPeople.length; i++) {
            Person person = new Person();

            for (int j = 0; j < matrixOfPeople[i].length; j++) {
                if (matrixOfPeople[i][j] == 1)
                    person.acquaintances.add(j);
                person.id = i;
            }
            people.add(person);
        }
        return people;
    }
}

class Person {
    int id;
    List<Integer> acquaintances = new ArrayList<>();
}
